package com.opefago.configuration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class Config(
        @JsonProperty("subnet")
        var subnet: String? = null,
        @JsonProperty("ipRange")
        val ipRange: String? = null,
        @JsonProperty("gateway")
        val gateway: String? = null,
        @JsonProperty("networkID")
        val networkID: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ipam(
        @JsonProperty("config")
        val config: List<Config>? = null,
        @JsonProperty("driver")
        val driver: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class NetworkConfiguration (
        var name: String = "",
        val driver: String = "bridge",
        val enableIpv6: Boolean = false,
        val internal: Boolean = false,
        val attachable: Boolean = false,
        val ipam: Ipam = Ipam(),
        val options: List<String> = emptyList()

)