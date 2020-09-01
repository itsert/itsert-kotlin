package com.itsert.resources.volume

import com.github.dockerjava.api.DockerClient
import com.itsert.configuration.VolumeConfiguration
import mu.KLogging

class Volume private constructor(
        private val client: DockerClient,
        private val name: String) {
    companion object: KLogging(){
        fun create(client: DockerClient, config: VolumeConfiguration) : Volume{
            val name = client.createVolumeCmd()
                    .withDriver(config.driver)
                    .withName(config.name).exec().name
            return Volume(client, name)
        }
    }

    fun delete(){
        client.removeVolumeCmd(name).exec()
    }
}