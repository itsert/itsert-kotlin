package com.itsert.core.scriptengine.function

import com.itsert.core.scriptengine.visitor.Interpreter

interface Callable {
    fun arity() : Int
    fun invoke(interpreter: Interpreter, arguments: List<Any>): Any
}