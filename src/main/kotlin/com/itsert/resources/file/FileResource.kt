package com.itsert.resources.file

import com.github.dockerjava.api.DockerClient
import com.itsert.configuration.ServiceConfiguration
import com.itsert.resources.Resource
import com.itsert.resources.container.Container

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