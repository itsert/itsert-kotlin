package com.itsert.core.scriptengine.visitor

import com.itsert.core.Project
import com.itsert.core.scriptengine.environments.Scope
import com.itsert.core.scriptengine.function.Callable
import com.itsert.core.scriptengine.function.inbuilt.HttpFunction
import com.itsert.logger.ILogger
import com.itsert.core.scriptengine.parser.ITDLBaseVisitor
import com.itsert.core.scriptengine.parser.ITDLParser
import com.itsert.core.scriptengine.types.expression.*
import com.itsert.core.scriptengine.types.expression.builder.ExpressionBuilder
import com.itsert.core.scriptengine.visitor.returns.ReturnTypes
import com.itsert.exceptions.ReturnException
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token
import java.lang.IllegalStateException
import org.assertj.core.api.Assertions.assertThat
import java.lang.StringBuilder
import kotlin.math.abs

class Interpreter(private val filename:String) : ITDLBaseVisitor<Any>() {
    private var currentScope: Scope
    private val globalScope = Scope()
    private var paddingLength = 0

    init {
        currentScope = globalScope
        bakeFunctions()
    }

    private fun bakeFunctions(){
        currentScope.define("request", HttpFunction())
    }

    override fun visitScript(ctx: ITDLParser.ScriptContext?): Any {
        ctx?.dependencesDclrExpr()?.forEach {
            visit(it)
        }
//        visit(ctx?.setupStmt())
        try {
            ctx?.statement()?.forEach {
                visit(it)
            }
        }catch (error: RuntimeError){
            println(error)
        }

//        visit(ctx?.tearDownStmt())
        return ReturnTypes.NONE
    }

    override fun visitStmtAssert(ctx: ITDLParser.StmtAssertContext?): Any {
        assertThat((visit(ctx?.assertionExpr()) as BooleanExpression).value).isEqualTo(true)
        return ReturnTypes.NONE
    }

    override fun visitStmtTestDcl(ctx: ITDLParser.StmtTestDclContext?): Any {
        return visit(ctx?.testDclrExpr())
    }

    override fun visitStmtAssign(ctx: ITDLParser.StmtAssignContext?): Any {
        return super.visitStmtAssign(ctx)
    }

    override fun visitStmtSemi(ctx: ITDLParser.StmtSemiContext?): Any {
        return ReturnTypes.NONE
    }

    override fun visitStmtFunDcl(ctx: ITDLParser.StmtFunDclContext?): Any {
        return visit(ctx!!.functionDclrExpr())
    }

    override fun visitStmtCondtional(ctx: ITDLParser.StmtCondtionalContext?): Any {
        return visit(ctx?.conditionalExpr())
    }


    override fun visitStmtFor(ctx: ITDLParser.StmtForContext?): Any {
        return visit(ctx?.forStatement())
    }

    override fun visitStmtWhile(ctx: ITDLParser.StmtWhileContext?): Any {
        return visit(ctx?.whileStatement())
    }

    override fun visitWhileStatement(ctx: ITDLParser.WhileStatementContext?): Any {
        while (evaluateCondition(visit(ctx?.expr()))){
            visit(ctx?.thenStatement())
        }
        return ReturnTypes.NONE
    }

    override fun visitForStatement(ctx: ITDLParser.ForStatementContext?): Any {
        val list = when{
            ctx?.ID(1) != null -> evaluateList(currentScope.resolve(ctx.ID(1).text), ctx.ID(1).symbol)
            ctx?.rangeExpr() != null -> evaluateList(visit(ctx.rangeExpr()),  ctx.rangeExpr().INT(0).symbol)
            ctx?.list() != null -> evaluateList(visit(ctx.list()), ctx.list().L_SQUARE().symbol)
            ctx?.STRING() != null -> evaluateList(
                    StringExpression(ctx.STRING().text, ctx.STRING().symbol), ctx.STRING().symbol)
            else->throw RuntimeError("collection is required in for loops", ctx?.start)
        }
        if(list.isNotEmpty()) {
            val indexName = "\$index"
            currentScope.define(ctx.ID(0).text, list[0])
            currentScope.define(indexName, 0)
            for ((index, value) in list.withIndex()) {
                currentScope.assign(ctx.ID(0).text, value)
                currentScope.assign(indexName, index)
                visit(ctx.thenStatement())
            }
        }
        return ReturnTypes.NONE
    }



    private fun evaluateList(value: Any, token: Token) : Array<Expression>{
        return when (value) {
            is ListExpression -> value.value
            is StringExpression -> value.value
                    .map { StringExpression(it.toString(), token) }
                    .toTypedArray<Expression>()
            else -> throw RuntimeError("collection is required in for loops",
                    token)
        }
    }

    override fun visitStmtExpr(ctx: ITDLParser.StmtExprContext?): Any {
        return try {
            visit(ctx?.exprStatement())
        }catch (exp: IllegalStateException){
            throw RuntimeError("Invalid Statement!!", ctx?.start)
        }
    }

    override fun visitExprStatement(ctx: ITDLParser.ExprStatementContext?): Any {
        return visit(ctx?.expr())
    }

    override fun visitDependencesDclrExpr(ctx: ITDLParser.DependencesDclrExprContext?): Any {
        if(ctx!!.dependencesOptions().text == "services"){
            val services = ctx.stringList().ID().map {  it.text}
            Project.run {
                paddingLength = instance.startServices(services, ctx.start)
            }
        }
        return ReturnTypes.NONE
    }

    override fun visitStringList(ctx: ITDLParser.StringListContext?): Any {
        return super.visitStringList(ctx)
    }

    override fun visitVarDeclare(ctx: ITDLParser.VarDeclareContext?): Any {
        visit(ctx?.subDeclare())
        return ReturnTypes.NONE
    }

    override fun visitSubDeclare(ctx: ITDLParser.SubDeclareContext?): Any {
        return if(hasExpression(ctx)) {
            currentScope.define(ctx!!.ID().text, visit(ctx.expr()))
        }else{
            currentScope.define(ctx!!.ID().text, NoneExpression(ctx.ID().symbol))
            NoneExpression(ctx.ID().symbol)
        }
    }

    private fun hasExpression(ctx: ITDLParser.SubDeclareContext?): Boolean{
        return ctx?.expr() != null
    }

    override fun visitDependencesOptions(ctx: ITDLParser.DependencesOptionsContext?): Any {
        return super.visitDependencesOptions(ctx)
    }

    override fun visitFunctionCall(ctx: ITDLParser.FunctionCallContext?): Any {
        val function = currentScope.resolve(ctx!!.ID().text)

        val arguments = mutableListOf<Expression>()
        if(ctx.functionCallParameters() != null) {
            ctx.functionCallParameters().ID().forEach {
                arguments.add(currentScope.resolve(it.text) as Expression)
            }
            ctx.functionCallParameters().expr().forEach {
                arguments.add(visit(it) as Expression)
            }
        }
        if(function is Callable){
            if(arguments.size != function.arity()){
                throw RuntimeError(
                        "Expected ${function.arity()} arguments but got ${arguments.size} .",
                        ctx.L_PAREN().symbol
                )
            }

            return function.invoke(this, arguments)
        }
        else{
            throw RuntimeError("can only call functions", ctx.L_PAREN().symbol)
        }
    }


    override fun visitFunctionDclrExpr(ctx: ITDLParser.FunctionDclrExprContext?): Any {
        val function = FunctionExpression(ctx!!.FUNCTION().symbol, ctx, currentScope)
        currentScope.define(ctx.ID().text, function)
        return ReturnTypes.NONE
    }

    override fun visitTestDclrExpr(ctx: ITDLParser.TestDclrExprContext?): Any {
        ILogger.log("$filename -> ${ctx?.ID()?.text ?: ctx?.STRING()?.text}")
        return visit(ctx?.block())
    }

    override fun visitSetupStmt(ctx: ITDLParser.SetupStmtContext?): Any {
        return super.visitSetupStmt(ctx)
    }

    override fun visitTearDownStmt(ctx: ITDLParser.TearDownStmtContext?): Any {
        return super.visitTearDownStmt(ctx)
    }

    override fun visitAssertionExpr(ctx: ITDLParser.AssertionExprContext?): Any {
        return super.visitAssertionExpr(ctx)
    }

    override fun visitConditionalExpr(ctx: ITDLParser.ConditionalExprContext?): Any {
        if(evaluateCondition(visit(ctx?.expr()))){
            return visit(ctx?.thenStatement())
        }else if(ctx?.elseCondition() != null){
            ctx.elseCondition().forEach {
                if(it.IF() == null || evaluateCondition(visit(it.expr()))){
                    return visit(it.elseStatement())
                }
            }
        }
        return ReturnTypes.NONE
    }

    override fun visitElseCondition(ctx: ITDLParser.ElseConditionContext?): Any {
        return visit(ctx?.elseStatement())
    }

    override fun visitThenStatement(ctx: ITDLParser.ThenStatementContext?): Any {
        return visit(ctx?.block() ?: ctx?.statement())
    }

    override fun visitElseStatement(ctx: ITDLParser.ElseStatementContext?): Any {
        return visit(ctx?.block() ?: ctx?.statement())
    }

    private fun evaluateCondition(exp: Any): Boolean{
        return (exp as BooleanExpression).value
    }

    override fun visitExprListDcl(ctx: ITDLParser.ExprListDclContext?): Any {
        return visit(ctx?.list())
    }

    override fun visitExprParen(ctx: ITDLParser.ExprParenContext?): Any {
        return visit(ctx?.expr())
    }

    override fun visitExprNum(ctx: ITDLParser.ExprNumContext?): Any {
        return NumberExpression(ctx?.NUMBER()?.text?.toDouble()?:0.0, ctx?.NUMBER()?.symbol!!)
    }

    override fun visitExprListIndex(ctx: ITDLParser.ExprListIndexContext?): Any {
        val list = visit(ctx!!.ID())
        if(list is ListExpression){
            ExpressionBuilder.build(list.value[ctx.INT().text.toInt()], ctx.L_SQUARE().symbol)
        }else{
            throw RuntimeError(
                    "index operation not available for non-collection type",
                    ctx.L_SQUARE().symbol
            )
        }
        return super.visitExprListIndex(ctx)
    }

    override fun visitExprInt(ctx: ITDLParser.ExprIntContext?): Any {
        return IntExpression(ctx?.INT()?.text?.toInt()?:0, ctx!!.INT().symbol)
    }

    override fun visitExprNotEq(ctx: ITDLParser.ExprNotEqContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression != rightExpression, ctx.expr(0).start)
    }

    override fun visitExprRef(ctx: ITDLParser.ExprRefContext?): Any {
        return currentScope.resolve(ctx!!.ID().text)
    }

    override fun visitExprNot(ctx: ITDLParser.ExprNotContext?): Any {
        val rightExpression = visit(ctx?.expr()!!) as Expression
        return !rightExpression
    }

    override fun visitExprGt(ctx: ITDLParser.ExprGtContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression > rightExpression, ctx.expr(0).start)
    }

    override fun visitExprStr(ctx: ITDLParser.ExprStrContext?): Any {
        return StringExpression(ctx!!.STRING().text, ctx.STRING().symbol)
    }

    override fun visitExprEq(ctx: ITDLParser.ExprEqContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression == rightExpression, ctx.expr(0).start)
    }

    override fun visitExprAddSub(ctx: ITDLParser.ExprAddSubContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return if(ctx.ADD() != null)
            leftExpression + rightExpression
        else
            leftExpression - rightExpression
    }

    override fun visitExprAnd(ctx: ITDLParser.ExprAndContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return leftExpression.and(rightExpression)
    }

    override fun visitExprGte(ctx: ITDLParser.ExprGteContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression >= rightExpression, ctx.expr(0).start)
    }

    override fun visitExprLt(ctx: ITDLParser.ExprLtContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression < rightExpression, ctx.expr(0).start)
    }

    override fun visitExprOr(ctx: ITDLParser.ExprOrContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return leftExpression.or(rightExpression)
    }

    override fun visitExprFunCall(ctx: ITDLParser.ExprFunCallContext?): Any {
        return visit(ctx!!.functionCall())
    }

    override fun visitExprRange(ctx: ITDLParser.ExprRangeContext?): Any {
        return visit(ctx?.rangeExpr())
    }

    override fun visitRangeExpr(ctx: ITDLParser.RangeExprContext?): Any {
        val first = ctx?.INT(0)
        val second = ctx?.INT(1)
        val range = convertToRange(first!!.text.toInt(), second!!.text.toInt(), ctx.INT(0).symbol)
        return ListExpression(range.toTypedArray(), ctx.INT(0).symbol)
    }

    private fun convertToRange(from: Int, to: Int, token: Token) : List<IntExpression>{
        val intRange = IntExpression(from, token) .. IntExpression(to, token)
        return intRange.toList().map { IntExpression(it, token) }
    }

    override fun visitExpBool(ctx: ITDLParser.ExpBoolContext?): Any {
        return if(ctx?.bool()?.TRUE() != null)
            BooleanExpression(true, ctx.bool().start)
        else
            BooleanExpression(false, ctx!!.bool().start)
    }

    override fun visitExprMultDiv(ctx: ITDLParser.ExprMultDivContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return if(ctx.MULT() != null)
            leftExpression * rightExpression
        else
            leftExpression / rightExpression
    }

    override fun visitExprLte(ctx: ITDLParser.ExprLteContext?): Any {
        val leftExpression = visit(ctx?.expr(0)!!) as Expression
        val rightExpression = visit(ctx.expr(1)) as Expression
        return BooleanExpression(leftExpression <= rightExpression, ctx.expr(0).start)
    }

    override fun visitStmtBlock(ctx: ITDLParser.StmtBlockContext?): Any {
        return visit(ctx?.block())
    }

    override fun visitExprReAssign(ctx: ITDLParser.ExprReAssignContext?): Any {
        if(ctx?.subDeclare()?.ASSIGN() != null){
            currentScope.assign(ctx.subDeclare()?.ID()!!.text, visit(ctx.subDeclare().expr()))
        }else{
            println(currentScope.resolve(ctx?.subDeclare()?.ID()!!.text))
        }
        return ReturnTypes.NONE
    }

    fun executeBlock(ctx: ITDLParser.BlockContext?, scope: Scope){
        val previousScope = currentScope
        try {
            currentScope = scope
            ctx?.statement()?.forEach {
                visit(it)
            }
        }finally {
            currentScope = previousScope
        }
    }

    override fun visitBlock(ctx: ITDLParser.BlockContext?): Any {
        executeBlock(ctx, Scope(currentScope))
        return ReturnTypes.NONE
    }



    override fun visitList(ctx: ITDLParser.ListContext?): Any {
        val array = mutableListOf<Expression>()
        val values = ctx!!.value()
        values?.forEach {
            if(it.BOOL() != null) array.add(ExpressionBuilder.build(it.BOOL()?.text!!.toBoolean(), ctx.start))
            if(it.INT() != null) array.add(ExpressionBuilder.build(it.INT().text.toInt(), ctx.start))
            if(it.STRING() != null) array.add(ExpressionBuilder.build(it.STRING()?.text!!, ctx.start))
            if(it.NUMBER() != null) array.add(ExpressionBuilder.build(it.NUMBER()?.text!!.toDouble(), ctx.start))
        }
        return (ListExpression(array.toTypedArray(), ctx.L_SQUARE().symbol))
    }

    override fun visitValue(ctx: ITDLParser.ValueContext?): Any {
        return when{
            ctx?.ID() != null -> currentScope.resolve(ctx.ID().text)
            ctx?.INT() != null -> IntExpression(ctx.INT().text.toInt(), ctx.start)
            ctx?.NUMBER() != null -> NumberExpression(ctx.NUMBER().text.toDouble(), ctx.start)
            ctx?.BOOL() != null -> BooleanExpression(ctx.BOOL().text!!.toBoolean(), ctx.start)
            ctx?.STRING() != null -> StringExpression(ctx.STRING().text, ctx.start)
            else -> throw RuntimeError("Unrecognized value", ctx?.start)
        }
    }

    override fun visitPrintExpr(ctx: ITDLParser.PrintExprContext?): Any {
        val builder = StringBuilder(filename)
        val idx = abs(builder.length - paddingLength)
        for (i in 0..idx){
            builder.append(" ")
        }
        ILogger.log("$builder  |${visit(ctx!!.expr())}")
        return ReturnTypes.NONE
    }

    override fun visitReturnSt(ctx: ITDLParser.ReturnStContext?): Any {
        return visit(ctx!!.returnStmt())
    }

    override fun visitReturnStmt(ctx: ITDLParser.ReturnStmtContext?): Any {
        var expression = NoneExpression(ctx!!.RETURN().symbol) as Expression
        if(ctx.expr() != null){
            expression = visit(ctx.expr()) as Expression
        }
        throw ReturnException(expression)
    }

    override fun visitPrototypeExpr(ctx: ITDLParser.PrototypeExprContext?): Any {
        return PrototypeExpression(ctx!!.start, ctx, currentScope)
    }

    override fun visitProtoExpr(ctx: ITDLParser.ProtoExprContext?): Any {
        return visit(ctx!!.prototypeExpr())
    }
}