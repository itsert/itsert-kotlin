package com.itsert.resources.docker

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.PullImageResultCallback
import com.github.dockerjava.api.exception.NotFoundException
import com.itsert.configuration.ServiceConfiguration
import com.itsert.resources.Resource
import com.itsert.resources.container.Container


class DockerResource (
        private val client: DockerClient,
        private val config: ServiceConfiguration)
    : Resource {
    private var createdImage = false
    override fun pull(){
        try {
            val images = client.listImagesCmd().withImageNameFilter(config.image).exec()
            if (images.isEmpty()) {
                createdImage = true
                client.pullImageCmd(config.image!!).exec(PullImageResultCallback()).awaitCompletion()
            }

        }catch (e: NotFoundException){
            println("Image not found")
        }

    }

    override fun build(): Container {
        return Container.create(client, config)
    }

    override fun delete() {
        if(createdImage) {
            client.listImagesCmd().withImageNameFilter(config.image).exec().forEach {
                client.removeImageCmd(it.id).exec()
            }
        }
    }
}