package com.opefago.configuration

data class Configuration(
        var port: Int,
        var name: String,
        var src: String,
        var network: String?,
        var custom: Map<String, String>
){
    private constructor(builder: Builder): this(
            port = 0,
            name = "",
            src = "",
            network = null,
            custom = mapOf()
    )
    class Builder
}