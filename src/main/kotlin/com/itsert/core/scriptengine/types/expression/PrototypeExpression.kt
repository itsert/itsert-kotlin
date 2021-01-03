package com.itsert.core.scriptengine.types.expression

import com.itsert.core.scriptengine.environments.Scope
import com.itsert.core.scriptengine.parser.ITDLParser
import com.itsert.core.scriptengine.visitor.Interpreter
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class PrototypeExpression(
        override val token: Token,
        private val ctx: ITDLParser.PrototypeExprContext,
        private val scope: Scope
) : Expression(null, "prototype", token) {
    val fields = mutableMapOf<String, Any>()
    override fun arity(): Int {
        return 0
    }

    override fun invoke(interpreter: Interpreter, arguments: List<Any>): Any {
        val functionScope = Scope(scope)
//        if(ctx.formalParamters() != null) {
//            ctx.formalParamters().ID().forEachIndexed { index, terminalNode ->
//                functionScope.define(terminalNode.text, arguments[index])
//            }
//        }
//        try {
//            interpreter.executeBlock(ctx.block(), functionScope)
//        }catch (e: ReturnException){
//            return e.value
//        }
        return NoneExpression(ctx.start)
    }

    override fun toString(): String {
        return "<prototype ${ctx.start.text}>"
    }

    override operator fun get(expression: String): Any{
        if(fields.contains(expression)){
            return fields[expression]!!
        }
        throw RuntimeError("undefined property '$expression'", token)
    }
    override operator fun set(expression1: String, expression2: Expression): Expression{
//        fields[expression1] = expression2
    }
}