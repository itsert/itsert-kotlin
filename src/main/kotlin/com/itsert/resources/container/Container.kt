package com.itsert.resources.container

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.exception.NotFoundException
import com.github.dockerjava.api.model.Frame
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.itsert.configuration.ServiceConfiguration
import com.itsert.exceptions.ImageNotFoundException
import mu.KLogging

class Container private constructor (
        private val client: DockerClient,
        private val containerId: String
){
    companion object : KLogging(){
        fun create(
                client: DockerClient,
                config: ServiceConfiguration
                ) : Container {
            try {
                val portBindings = mutableListOf<PortBinding>()
                config.ports?.forEach{
                    portBindings.add(PortBinding.parse(it))
                }
                val containerCmd = client.createContainerCmd(config.image!!)
                        .withName(config.name)
                        .withHostConfig(HostConfig.newHostConfig()
                                .withPortBindings(portBindings))
                if(config.volumes != null){
                    containerCmd.withVolumes()
                }
                if(config.command != null){
                    containerCmd.withCmd(config.command)
                }
                if(config.environment != null){
                    containerCmd.withEnv(config.environment)
                }

                val containerId = containerCmd.exec().id
                client.logContainerCmd(containerId)
                        .withStdOut(true)
                        .withStdErr(true)
                        .exec(object : ResultCallback.Adapter<Frame?>() {
                            @Override
                            fun onNext(frame: Frame) {
                                logger.info(frame.payload.toString())
                            }
                        })
                return Container(client, containerId)
            }catch (e: NotFoundException){
                throw ImageNotFoundException(e.message)
            }
        }

        fun createFromId(client: DockerClient, containerId: String): Container {
            return Container(client, containerId)
        }
    }

    fun id(): String = client.inspectContainerCmd(containerId).exec().id

    fun name(): String = client.inspectContainerCmd(containerId).exec().name

    fun port(){

    }
    fun isRunning() =
        client.inspectContainerCmd(containerId).exec().state.running

    fun isRestarting() =
        client.inspectContainerCmd(containerId).exec().state.restarting

    fun isPaused() = client.inspectContainerCmd(containerId).exec().state.paused

    fun kill(){
        client.killContainerCmd(containerId).exec()
    }
    fun restart(){
        client.restartContainerCmd(containerId).exec()
    }
    fun unpause(){
        client.unpauseContainerCmd(containerId).exec()
    }
    fun remove(){
        client.removeContainerCmd(containerId).exec()
    }

    fun createExec(command: List<String>): String{
        return client.execCreateCmd(containerId)
                .withCmd(*command.toTypedArray())
                .exec().id
    }
    fun startExec(execId: String){
        client.execStartCmd(execId)
                .exec(object : ResultCallback.Adapter<Frame>(){})
                .awaitCompletion()

    }
    fun start(){
        client.startContainerCmd(containerId).exec()
    }
    fun stop(){
        client.stopContainerCmd(containerId).exec()
    }

    fun imageId() = client.inspectImageCmd(containerId).imageId
}