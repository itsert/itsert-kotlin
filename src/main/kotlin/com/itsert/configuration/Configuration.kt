package com.itsert.configuration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Configuration(
        @JsonProperty("version")
        val version: String = "",
        @JsonProperty("services")
        val services: Map<String, ServiceConfiguration>? = mapOf(),
        @JsonProperty("networks")
        val networks: Map<String, NetworkConfiguration>? = mapOf(),
        @JsonProperty("volumes")
        val volumes: Map<String, Any>? = mapOf()
)