package com.itsert.utils

import com.itsert.exceptions.InvalidPairException

class Utils {
    companion object {
        const val NAMESPACE = "default"
        const val DEFAULT_NETWORK = "itsert-net"
        val INTEGRATION_CONFIGS =
                listOf("IntegrationConfig", "IntegrationConfig.yml", "IntegrationConfig.yaml")

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

        fun splitPortPair(string: String): Pair<String, String>{
            val pair = string.split(":")
            if(pair.size != 2){
                throw InvalidPairException("Invalid key/value pair")
            }
            return Pair(pair[0], pair[1])
        }
    }

}