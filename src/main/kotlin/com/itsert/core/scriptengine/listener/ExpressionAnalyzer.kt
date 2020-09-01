//package com.opefago.core.integrationscript.listener
//
//import com.opefago.core.integrationscript.environments.Scope
//import com.opefago.core.integrationscript.parser.ITDLBaseListener
//import com.opefago.core.integrationscript.parser.ITDLParser
//import com.opefago.core.integrationscript.types.expression.*
//import com.opefago.core.integrationscript.types.expression.builder.ExpressionBuilder
//import org.antlr.v4.runtime.tree.ParseTree
//import org.antlr.v4.runtime.tree.ParseTreeProperty
//import java.util.*
//
//
//class ExpressionAnalyzer : ITDLBaseListener(){
//    lateinit var currentScope: Scope
//    val expression = ParseTreeProperty<Stack<Expression>>()
//    val scopes: ParseTreeProperty<Scope> = ParseTreeProperty()
//    val globalScope = Scope(null)
//
//    private fun setExpression(ctx: ParseTree, expr: Expression){
//        var stack = expression.get(ctx)
//        if(stack == null){
//            stack = Stack()
//        }
//        stack.push(expr)
//        expression.put(ctx, stack)
//    }
//
//    private fun getExpression(ctx: ParseTree) : Stack<Expression>{
//        return expression.get(ctx)
//    }
//
//    override fun enterStmtTestDcl(ctx: ITDLParser.StmtTestDclContext?) {
//        currentScope = globalScope
//        super.enterStmtTestDcl(ctx)
//    }
//
//    override fun exitStmtTestDcl(ctx: ITDLParser.StmtTestDclContext?) {
//        super.exitStmtTestDcl(ctx)
//    }
//
//    override fun enterStmtFunDcl(ctx: ITDLParser.StmtFunDclContext?) {
//        super.enterStmtFunDcl(ctx)
//    }
//
//    override fun exitStmtFunDcl(ctx: ITDLParser.StmtFunDclContext?) {
//        super.exitStmtFunDcl(ctx)
//    }
//
//    override fun enterSubDeclare(ctx: ITDLParser.SubDeclareContext?) {
//        super.enterSubDeclare(ctx)
//    }
//
//    override fun exitSubDeclare(ctx: ITDLParser.SubDeclareContext?) {
//        try {
//            if(ctx?.expr() != null) {
////            println("${ctx?.ID()} : ${ctx.expr().text}")
//                println("${ctx.start.line}, ${ctx.start.charPositionInLine}: ${ctx.ID()} : ${getExpression(ctx).pop()}")
//            }else{
//                println("${ctx?.start?.line}, ${ctx?.start?.charPositionInLine}:${ctx?.ID()} : ${NoneExpression()}")
//            }
//        }catch (exp: RuntimeException){
//            println("Found error")
//        }
//    }
//
//    override fun enterSetupStmt(ctx: ITDLParser.SetupStmtContext?) {
//        super.enterSetupStmt(ctx)
//    }
//
//    override fun exitSetupStmt(ctx: ITDLParser.SetupStmtContext?) {
//        super.exitSetupStmt(ctx)
//    }
//
//    override fun enterTearDownStmt(ctx: ITDLParser.TearDownStmtContext?) {
//        super.enterTearDownStmt(ctx)
//    }
//
//    override fun exitTearDownStmt(ctx: ITDLParser.TearDownStmtContext?) {
//        super.exitTearDownStmt(ctx)
//    }
//
//    override fun enterExprRef(ctx: ITDLParser.ExprRefContext?) {
//        super.enterExprRef(ctx)
//    }
//
//    override fun exitExprRef(ctx: ITDLParser.ExprRefContext?) {
//        super.exitExprRef(ctx)
//    }
//
//    override fun enterBlock(ctx: ITDLParser.BlockContext?) {
//        super.enterBlock(ctx)
//    }
//
//    override fun exitBlock(ctx: ITDLParser.BlockContext?) {
//        super.exitBlock(ctx)
//    }
//
//    override fun enterExprListDcl(ctx: ITDLParser.ExprListDclContext?) {
//        super.enterExprListDcl(ctx)
//    }
//
//    override fun exitExprListDcl(ctx: ITDLParser.ExprListDclContext?) {
//        try {
//            val array = mutableListOf<Expression>()
//            val values = ctx?.list()?.value()
//            values?.forEach {
//                if(it.BOOL() != null) array.add(ExpressionBuilder.build(it.BOOL()?.text!!.toBoolean()))
//                if(it.INT() != null) array.add(ExpressionBuilder.build(it.INT().text.toInt()))
//                if(it.STRING() != null) array.add(ExpressionBuilder.build(it.STRING()?.text!!))
//                if(it.NUMBER() != null) array.add(ExpressionBuilder.build(it.NUMBER()?.text!!.toDouble()))
//            }
//            setExpression( ctx?.parent!!, ListExpression(array.toTypedArray()))
//            super.exitExprListDcl(ctx)
//        }catch (exp: RuntimeException){
//            println("Found error")
//        }
//    }
//
//    override fun enterExprParen(ctx: ITDLParser.ExprParenContext?) {
//        super.enterExprParen(ctx)
//    }
//
//    override fun exitExprParen(ctx: ITDLParser.ExprParenContext?) {
//        setExpression(ctx?.parent!!, getExpression(ctx).pop())
//    }
//
//    override fun enterExprNum(ctx: ITDLParser.ExprNumContext?) {
//        super.enterExprNum(ctx)
//    }
//
//    override fun exitExprNum(ctx: ITDLParser.ExprNumContext?) {
//        setExpression(ctx?.parent!!, NumberExpression(ctx.NUMBER()?.text?.toDouble()?:0.0))
//    }
//
//    override fun enterExprListIndex(ctx: ITDLParser.ExprListIndexContext?) {
//        super.enterExprListIndex(ctx)
//    }
//
//    override fun exitExprListIndex(ctx: ITDLParser.ExprListIndexContext?) {
//        super.exitExprListIndex(ctx)
//    }
//
//    override fun enterExprInt(ctx: ITDLParser.ExprIntContext?) {
//        super.enterExprInt(ctx)
//    }
//
//    override fun exitExprInt(ctx: ITDLParser.ExprIntContext?) {
//        setExpression(ctx?.parent!!, IntExpression(ctx.INT()?.text?.toInt()?:0))
//    }
//
//    override fun enterExprNotEq(ctx: ITDLParser.ExprNotEqContext?) {
//        super.enterExprNotEq(ctx)
//    }
//
//    override fun exitExprNotEq(ctx: ITDLParser.ExprNotEqContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression != rightExpression))
//    }
//
//    override fun enterExprNot(ctx: ITDLParser.ExprNotContext?) {
//        super.enterExprNot(ctx)
//    }
//
//    override fun exitExprNot(ctx: ITDLParser.ExprNotContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        setExpression( ctx.parent, !leftExpression)
//    }
//
//    override fun enterExprGt(ctx: ITDLParser.ExprGtContext?) {
//        super.enterExprGt(ctx)
//    }
//
//    override fun exitExprGt(ctx: ITDLParser.ExprGtContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression > rightExpression))
//    }
//
//    override fun enterExprStr(ctx: ITDLParser.ExprStrContext?) {
//        super.enterExprStr(ctx)
//    }
//
//    override fun exitExprStr(ctx: ITDLParser.ExprStrContext?) {
//        setExpression(ctx?.parent!!, StringExpression(ctx.STRING()?.text?:""))
//    }
//
//    override fun enterExprEq(ctx: ITDLParser.ExprEqContext?) {
//        super.enterExprEq(ctx)
//    }
//
//    override fun exitExprEq(ctx: ITDLParser.ExprEqContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression == rightExpression))
//    }
//
//    override fun enterExprAddSub(ctx: ITDLParser.ExprAddSubContext?) {
//        super.enterExprAddSub(ctx)
//    }
//
//    override fun exitExprAddSub(ctx: ITDLParser.ExprAddSubContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        if(ctx.ADD() != null)
//            setExpression( ctx.parent, rightExpression + leftExpression)
//        if(ctx.SUB() != null)
//            setExpression( ctx.parent, rightExpression - leftExpression)
//    }
//
//    override fun enterExprAnd(ctx: ITDLParser.ExprAndContext?) {
//        super.enterExprAnd(ctx)
//    }
//
//    override fun exitExprAnd(ctx: ITDLParser.ExprAndContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, leftExpression.and(rightExpression))
//    }
//
//    override fun enterExprGte(ctx: ITDLParser.ExprGteContext?) {
//
//    }
//
//    override fun exitExprGte(ctx: ITDLParser.ExprGteContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression >= rightExpression))
//    }
//
//    override fun enterExprLt(ctx: ITDLParser.ExprLtContext?) {
//        super.enterExprLt(ctx)
//    }
//
//    override fun exitExprLt(ctx: ITDLParser.ExprLtContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression < rightExpression))
//    }
//
//    override fun enterExprOr(ctx: ITDLParser.ExprOrContext?) {
//        super.enterExprOr(ctx)
//    }
//
//    override fun exitExprOr(ctx: ITDLParser.ExprOrContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, leftExpression.or(rightExpression))
//    }
//
//    override fun enterExprFunCall(ctx: ITDLParser.ExprFunCallContext?) {
//        super.enterExprFunCall(ctx)
//    }
//
//    override fun exitExprFunCall(ctx: ITDLParser.ExprFunCallContext?) {
//        super.exitExprFunCall(ctx)
//    }
//
//    override fun enterExprRange(ctx: ITDLParser.ExprRangeContext?) {
//    }
//
////    override fun exitExprRange(ctx: ITDLParser.ExprRangeContext?) {
////        val first = ctx?.INT(0)
////        val second = ctx?.INT(1)
////        val range = convertToRange(first!!.text.toInt(), second!!.text.toInt())
////        setExpression( ctx.parent, ListExpression(range.toTypedArray()))
////    }
//    private fun convertToRange(from: Int, to: Int) : List<IntExpression>{
//        val intRange = IntExpression(from) .. IntExpression(to)
//        return intRange.toList().map { IntExpression(it) }
//    }
//
//    override fun enterExpBool(ctx: ITDLParser.ExpBoolContext?) {
//
//        super.enterExpBool(ctx)
//    }
//
//    override fun exitExpBool(ctx: ITDLParser.ExpBoolContext?) {
//        if(ctx?.bool()?.TRUE() != null)
//            setExpression(ctx.parent!!, BooleanExpression(true))
//        else
//            setExpression(ctx?.parent!!, BooleanExpression(false))
//    }
//
//    override fun enterExprMultDiv(ctx: ITDLParser.ExprMultDivContext?) {
//        super.enterExprMultDiv(ctx)
//    }
//
//    override fun exitExprMultDiv(ctx: ITDLParser.ExprMultDivContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        if(ctx.MULT() != null)
//            setExpression( ctx.parent, rightExpression * leftExpression)
//        if(ctx.DIV() != null)
//            setExpression( ctx.parent, rightExpression / leftExpression)
//    }
//
//    override fun enterExprLte(ctx: ITDLParser.ExprLteContext?) {
//        super.enterExprLte(ctx)
//    }
//
//    override fun exitExprLte(ctx: ITDLParser.ExprLteContext?) {
//        val leftExpression = getExpression(ctx!!).pop()
//        val rightExpression = getExpression(ctx).pop()
//        setExpression( ctx.parent, BooleanExpression(leftExpression <= rightExpression))
//    }
//
//    override fun enterFunctionCall(ctx: ITDLParser.FunctionCallContext?) {
//        super.enterFunctionCall(ctx)
//    }
//
//    override fun exitFunctionCall(ctx: ITDLParser.FunctionCallContext?) {
//        super.exitFunctionCall(ctx)
//    }
//}