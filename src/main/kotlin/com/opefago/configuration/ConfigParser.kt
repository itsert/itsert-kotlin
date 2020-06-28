package com.opefago.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import mu.KLogging
import java.io.File


class ConfigParser {
    companion object : KLogging()
    fun parse(file: File) : Configuration{
        val mapper = ObjectMapper(YAMLFactory())
        mapper.findAndRegisterModules()

        val result = mapper.readValue(file, Configuration::class.java)
        logger.info("Yaml output $result")
        return result
    }
}