package com.itsert.exceptions

import org.antlr.v4.runtime.Token
import java.lang.RuntimeException

class RuntimeError (message: String, token: Token?)
    : RuntimeException(
        if(token == null)message else "Error: <${token.line}:${token.charPositionInLine}> $message"
){
    constructor(message: String) : this(message, null)
}