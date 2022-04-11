package com.itsert.utils

class StringUtils {
    companion object{
        fun stripQuotes(string: String): String{
            var value = string
            return if(value.length > 1 && value.startsWith("\"") && value.endsWith("\""))
                value.substring(1, value.length-1)
            else if(value.length > 1 && value.startsWith("\'") && value.endsWith("\'"))
                value.substring(1, value.length-1)
            else value
        }
    }
}