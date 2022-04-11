package com.itsert.core.scriptengine.types.expression

import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class BooleanExpression(override var value: Boolean, override val token: Token)
    : Expression(value, "Boolean", token) {
    override fun not() : Expression{
        return BooleanExpression(!value, token)
    }

    override fun compareTo(expression: Expression): Int{
        if(expression is BooleanExpression)
            return value.compareTo(expression.value)
        else throw RuntimeError("Invalid $name Comparison on type ${expression.name}", token)
    }

    override fun and(expression: Expression): Expression{
        if(expression is BooleanExpression){
            value = value && expression.value
            return this
        }
        else throw RuntimeError("Invalid $name Boolean on type ${expression.name}", token)
    }

    override fun or(expression: Expression): Expression{
        if(expression is BooleanExpression){
            value = value || expression.value
            return this
        }
        else throw RuntimeError("Invalid $name Comparison on type ${expression.name}", token)
    }
}