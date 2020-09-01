package com.itsert.core.scriptengine.types.expression

import org.antlr.v4.runtime.Token

class NoneExpression(override val token: Token): Expression(null, "None", token){
    override fun toString(): String {
        return "None"
    }
}