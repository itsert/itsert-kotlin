package com.itsert.core.scriptengine.types.expression

import com.itsert.core.scriptengine.environments.Scope
import com.itsert.core.scriptengine.parser.ITDLParser
import com.itsert.core.scriptengine.visitor.Interpreter
import com.itsert.exceptions.ReturnException
import org.antlr.v4.runtime.Token

class FunctionExpression(
        override val token: Token,
        private val ctx: ITDLParser.FunctionDclrExprContext,
        private val scope: Scope)
    : Expression(null, "function", token) {

    override fun arity(): Int {
        if(ctx.formalParamters() != null)
            return ctx.formalParamters().ID().size
        return 0
    }

    override fun invoke(interpreter: Interpreter, arguments: List<Any>): Any {
        val functionScope = Scope(scope)
        if(ctx.formalParamters() != null) {
            ctx.formalParamters().ID().forEachIndexed { index, terminalNode ->
                functionScope.define(terminalNode.text, arguments[index])
            }
        }
        try {
            interpreter.executeBlock(ctx.block(), functionScope)
        }catch (e: ReturnException){
            return e.value
        }
        return NoneExpression(ctx.FUNCTION().symbol)
    }

    override fun toString(): String {
        return "<function ${ctx.ID().text}>"
    }
}