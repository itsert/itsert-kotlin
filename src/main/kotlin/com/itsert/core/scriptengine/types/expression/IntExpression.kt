package com.itsert.core.scriptengine.types.expression

import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class IntExpression(override var value: Int, override val token: Token)
    : Expression(value, "Integer", token){
    //TODO optimize integer operations, currently creates a new Object for every operation
    override fun plus(expression: Expression): Expression {
        if(expression is IntExpression)
            return IntExpression(expression.value + value, token)
        else throw RuntimeError("Invalid $name addition on type ${expression.name}", token)
    }

    override fun minus(expression: Expression): Expression {
        if(expression is IntExpression)
            return IntExpression(value - expression.value, token)
        else throw RuntimeError("Invalid $name subtraction on type ${expression.name}", token)
    }
    override fun times(expression: Expression): Expression {
        if(expression is IntExpression)
            return IntExpression(expression.value * value, token)
        else throw RuntimeError("Invalid $name multiplication on type ${expression.name}", token)
    }

    override fun div(expression: Expression): Expression {
        if(expression is IntExpression)
            return IntExpression(value / expression.value, token)
        else throw RuntimeError("Invalid $name division on type ${expression.name}", token)
    }

    override fun rem(expression: Expression): Expression {
        if(expression is IntExpression)
            return IntExpression( value % expression.value, token)
        else throw RuntimeError("Invalid $name Modulus on type ${expression.name}", token)
    }

    override fun compareTo(expression: Expression): Int{
        if(expression is IntExpression)
            return value.compareTo(expression.value)
        else throw RuntimeError("Invalid $name Modulus on type ${expression.name}", token)
    }

    override fun rangeTo(expression: Expression): IntRange {
        if(expression is IntExpression)
            return value .. expression.value
        else throw RuntimeError("Invalid $name range on type ${expression.name}", token)
    }
}