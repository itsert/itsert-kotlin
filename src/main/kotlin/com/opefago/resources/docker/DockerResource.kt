package com.opefago.resources.docker

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.PullImageResultCallback
import com.github.dockerjava.api.exception.NotFoundException
import com.opefago.configuration.ServiceConfiguration
import com.opefago.resources.Resource
import com.opefago.utils.Container


class DockerResource (private val client: DockerClient, private val config: ServiceConfiguration)
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
        return Container.create(client, config, null, null)
    }

    override fun delete() {
        if(createdImage) {
            client.listImagesCmd().withImageNameFilter(config.image).exec().forEach {
                client.removeImageCmd(it.id).exec()
            }
        }
    }
}