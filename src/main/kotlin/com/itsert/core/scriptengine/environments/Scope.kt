package com.itsert.core.scriptengine.environments

import com.itsert.exceptions.RuntimeError

class Scope(private val enclosing: Scope?=null) {
    private var values = HashMap<String, Any>()


    fun define(name: String, value: Any) {
        values[name] = value
    }

    fun assign(name: String, value: Any) {
        if (values.containsKey(name)) {
            values[name] = value
            return
        }

        if (enclosing != null) {
            enclosing.assign(name, value)
            return
        }

        throw RuntimeError("Undefined variable '$name'.")
    }

    fun resolve(name: String): Any {
        if(values.containsKey(name)){
            return values[name]!!
        }

        if(enclosing != null) return enclosing.resolve(name)

        throw RuntimeError("Undefined variable '$name'.")
    }
}