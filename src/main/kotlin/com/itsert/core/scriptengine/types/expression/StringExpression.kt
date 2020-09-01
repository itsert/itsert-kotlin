package com.itsert.core.scriptengine.types.expression

import com.itsert.utils.StringUtils
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class StringExpression(override var value: String, override val token: Token)
    : Expression(value, "String", token) {
    init {
        value = StringUtils.stripQuotes(value)
    }
    override fun compareTo(expression: Expression): Int{
        if(expression is StringExpression)
            return value.compareTo(expression.value)
        else throw RuntimeError("Invalid $name Comparison on type ${expression.name}", token)
    }

    override fun contains(expression: Expression): Boolean {
        if(expression is StringExpression)
            return value.contains(expression.value)
        else throw RuntimeError("Invalid $name Comparison on type ${expression.name}", token)
    }
}