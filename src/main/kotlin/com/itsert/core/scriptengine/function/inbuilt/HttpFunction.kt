package com.itsert.core.scriptengine.function.inbuilt

import com.itsert.core.scriptengine.function.Callable
import com.itsert.core.scriptengine.types.expression.NoneExpression
import com.itsert.core.scriptengine.types.expression.StringExpression
import com.itsert.core.scriptengine.visitor.Interpreter
import okhttp3.OkHttpClient
import okhttp3.Request


class HttpFunction: Callable {
    override fun arity(): Int {
        return 1
    }

    override fun invoke(interpreter: Interpreter, arguments: List<Any>): Any {
        val url = arguments[0] as StringExpression
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url.value)
                .build()
        val call = client.newCall(request)
        val response = call.execute()
        return if(response.body() != null) {
            StringExpression(response.body()!!.string())
        }else{
            NoneExpression()
        }
    }
}