package com.opefago.utils

import com.opefago.exceptions.InvalidPairException

class Utils {
    companion object {
        fun gitNameFromUrl(url: String): String {
            var pattern = "^(https|git)(://|@)([^/:]+)[/:]([^/:]+)/(.+).git\$"
            val regex = pattern.toRegex()
            val match = regex.matchEntire(url)
            return match?.groups?.get(4)?.value!!
        }

        fun splitPair(string: String): Pair<String, String>{
            val pair = string.split(":(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)")
            if(pair.size != 2){
                throw InvalidPairException("Invalid key/value pair")
            }
            return Pair(pair[0], pair[1])
        }
    }

}