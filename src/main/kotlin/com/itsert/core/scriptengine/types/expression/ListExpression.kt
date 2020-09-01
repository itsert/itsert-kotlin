package com.itsert.core.scriptengine.types.expression

import com.itsert.core.scriptengine.types.expression.builder.ExpressionBuilder
import org.antlr.v4.runtime.Token

class ListExpression(override val value: Array<Expression>, override val token: Token) : Expression(value, "List") {
    override fun contains(expression: Expression): Boolean{
        return value.contains(expression.value)
    }
    override fun get(expression: Int): Expression{
        return ExpressionBuilder.build(value[expression], token)
    }
    override fun set(expression1: Int, expression2: Expression): Expression{
        value[expression1] = expression2
        return this
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