package com.itsert.configuration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VolumeConfiguration(
        val driver: String,
        var name: String
){
    fun withName(name: String): VolumeConfiguration{
        this.name = name
        return this
    }
}