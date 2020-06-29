package com.opefago.resources.file

import com.github.dockerjava.api.DockerClient
import com.opefago.configuration.ServiceConfiguration
import com.opefago.resources.Resource
import com.opefago.resources.container.Container

class FileResource(
        private val client: DockerClient,
        private val config: ServiceConfiguration) : Resource {
    override fun pull() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build(): Container {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}