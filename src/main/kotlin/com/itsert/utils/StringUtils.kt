package com.itsert.utils

class StringUtils {
    companion object{
        fun stripQuotes(string: String): String{
            var value = string
            if(value.length > 1 && value.startsWith("\""))
                value = value.substring(1, value.length-1)
            return value
        }
    }
}