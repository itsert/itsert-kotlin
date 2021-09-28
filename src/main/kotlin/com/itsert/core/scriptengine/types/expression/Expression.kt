package com.itsert.core.scriptengine.types.expression

import com.itsert.core.scriptengine.function.Callable
import com.itsert.core.scriptengine.visitor.Interpreter
import com.itsert.exceptions.RuntimeError
import org.antlr.v4.runtime.Token

abstract class Expression(
        open val value: Any?,
        val name: String,
        open val token: Token? = null
) : Callable{
    open operator fun plus(expression: Expression): Expression{
        throw RuntimeError("Addition operation cannot be performed on $name", token)
    }
    open operator fun minus(expression: Expression): Expression{
        throw RuntimeError("Addition operation cannot be performed on $name", token)
    }
    open operator fun rangeTo(expression: Expression): IntRange{
        throw RuntimeError("Range operation cannot be performed on $name", token)
    }
    open operator fun times(expression: Expression): Expression{
        throw RuntimeError("Multiplication operation cannot be performed on $name", token)
    }
    open operator fun div(expression: Expression): Expression{
        throw RuntimeError("division operation cannot be performed on $name", token)
    }
    open operator fun rem(expression: Expression): Expression{
        throw RuntimeError("Modulus operation cannot be performed on $name", token)
    }

    override fun arity(): Int {
        return 0
    }

    override operator fun invoke(interpreter: Interpreter, arguments: List<Any>): Any {
        throw RuntimeError("$name cannot be called", token)
    }

    open operator fun not() : Expression{
        throw RuntimeError("Not operation cannot be performed on $name", token)
    }

    open operator fun compareTo(expression: Expression): Int{
        throw RuntimeError("comparison operation cannot be performed on $name", token)
    }

    open operator fun contains(expression: Expression): Boolean{
        throw RuntimeError("List operation cannot be performed on $name", token)
    }
    open operator fun get(expression: Any): Any{
        throw RuntimeError("List get operation cannot be performed on $name", token)
    }
    open operator fun set(expression1: Any, expression2: Expression): Expression{
        throw RuntimeError("Addition operation cannot be performed on $name", token)
    }

    open fun and(expression: Expression): Expression{
        throw RuntimeError("Boolean operation cannot be performed on $name", token)
    }
    open fun or(expression: Expression): Expression{
        throw RuntimeError("Boolean operation cannot be performed on $name", token)
    }

    override fun toString(): String {
        return "$name(value=$value)"
    }

}