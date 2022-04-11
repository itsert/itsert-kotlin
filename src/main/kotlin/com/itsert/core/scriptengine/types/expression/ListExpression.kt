package com.itsert.core.scriptengine.types.expression

import com.itsert.core.scriptengine.types.expression.builder.ExpressionBuilder
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class ListExpression(override val value: Array<Expression>, override val token: Token) : Expression(value, "List") {
    override fun contains(expression: Expression): Boolean{
        return value.contains(expression.value)
    }
    override fun get(expression: Any): Expression{
        if(expression is Int)
        return ExpressionBuilder.build(value[expression], token)
        else
            throw RuntimeError("Invalid index type, expecting integer")
    }
    override fun set(expression1: Any, expression2: Expression): Expression{
        if(expression1 is Int) {
            value[expression1] = expression2
            return this
        }
        else
            throw RuntimeError("Invalid index type, expecting an integer")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListExpression

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    override fun toString(): String {
        return "$name[${value.joinToString(",")}]";
    }
}