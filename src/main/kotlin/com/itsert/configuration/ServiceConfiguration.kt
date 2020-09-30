package com.itsert.configuration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServiceConfiguration(
        val ports: List<String>? = null,
        var name: String = "",
        val repository: String? = null,
        val image: String? = null,
        val tag: String? = null,
        val hash: String? = null,
        val replica: Int = 1,
        val expose: List<String>? = null,
        val networks: List<String>? = null,
        val volumes: List<String>? = null,
        val command: String? = null,
        val environment: List<String>? = null
){
    fun withName(name: String): ServiceConfiguration{
        this.name = name
        return this
    }
}