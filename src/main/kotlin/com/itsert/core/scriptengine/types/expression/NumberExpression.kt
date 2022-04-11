package com.itsert.core.scriptengine.types.expression

import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class NumberExpression(override var value: Double, override val token: Token)
    : Expression(value, "Number", token){
    override fun plus(expression: Expression): Expression {
        if(expression is IntExpression || expression is NumberExpression) {
            val temp = expression.value as Double
            value += temp
            return this
        }
        else throw RuntimeError("Invalid $name addition on type ${expression.name}", token)
    }

    override fun minus(expression: Expression): Expression {
        if(expression is IntExpression || expression is NumberExpression) {
            val temp = expression.value as Double
            value -= temp
            return this
        }
        else throw RuntimeError("Invalid $name subtraction on type ${expression.name}", token)
    }
    override fun times(expression: Expression): Expression {
        if(expression is IntExpression || expression is NumberExpression) {
            val temp = expression.value as Double
            value *= temp
            return this
        }
        else throw RuntimeError("Invalid $name multiplication on type ${expression.name}", token)
    }

    override fun div(expression: Expression): Expression {
        if(expression is IntExpression || expression is NumberExpression) {
            val temp = expression.value as Double
            value /= temp
            return this
        }
        else throw RuntimeError("Invalid $name division on type ${expression.name}", token)
    }


    override fun compareTo(expression: Expression): Int{
        if(expression is IntExpression || expression is NumberExpression) {
            val temp = expression.value as Double
            return value.compareTo(temp)
        }
        else throw RuntimeError("Invalid $name Modulus on type ${expression.name}", token)
    }
}