package com.itsert.core.scriptengine.types.expression.builder

import com.itsert.core.scriptengine.types.expression.*
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

class ExpressionBuilder {
    companion object {
        fun build(value: Any?, token: Token): Expression {
            return when (value) {
                is Int -> IntExpression(value, token)
                is Double -> NumberExpression(value, token)
                is String -> StringExpression(value, token)
                is Boolean -> BooleanExpression(value, token)
                null -> NoneExpression(token)
                else -> throw RuntimeError("Unrecognised Expression", token)
            }
        }
    }
}