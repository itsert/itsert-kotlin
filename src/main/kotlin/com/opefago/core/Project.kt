package com.opefago.core

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.opefago.configuration.ConfigParser
import com.opefago.configuration.NetworkConfiguration
import com.opefago.configuration.VolumeConfiguration
import com.opefago.exceptions.ImageNotFoundException
import com.opefago.utils.Container
import com.opefago.utils.Network
import com.opefago.utils.Volume
import mu.KLogging
import java.io.File
import kotlin.system.exitProcess


class Project private constructor(){
    private val dockerClient: DockerClient
    init {
        val standard: DockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .build()
//        val custom: DockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
//                .withDockerHost("tcp://localhost:2376")
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("/home/user/.docker")
//                .withRegistryUsername(registryUser)
//                .withRegistryPassword(registryPass)
//                .withRegistryEmail(registryMail)
//                .withRegistryUrl(registryUrl)
//                .build()
        val httpClient: DockerHttpClient = ApacheDockerHttpClient.Builder()
                .dockerHost(standard.dockerHost)
                .sslConfig(standard.sslConfig)
                .build()
        dockerClient = DockerClientImpl.getInstance(standard, httpClient)
    }
    private object HOLDER {
        val INSTANCE = Project()
    }

    companion object : KLogging() {
        val instance: Project by lazy { HOLDER.INSTANCE }
        val INTEGRATION_CONFIGS = listOf<String>("IntegrationConfig", "IntegrationConfig.yml", "IntegrationConfig.yaml")
    }
    private lateinit var containers: Map<String, Container>
//    private lateinit var services: Map<String, Service>
    private lateinit var network: Map<String, Network>
    private lateinit var volumes: Map<String, Volume>
    fun run(){
        try {

            val parser = ConfigParser()
            val path = INTEGRATION_CONFIGS.first {
                File(it).exists()
            }
            val file = File(path)
            val configuration = parser.parse(file)
//            val dockerResource = DockerResource(dockerClient, configuration)
//            logger.info("Pulling image")
//            dockerResource.pull()
//            logger.info("Creating container")
//            val container = dockerResource.build()
//            logger.info("Starting container")
//            container.start()
//            logger.info("Stopping container")
//            container.stop()
//            logger.info("Removing container")
//            container.remove()
//            logger.info("Deleting image")
//            dockerResource.delete()
//            logger.info("Stopping container")
//            container.stop()
        }catch (e: ImageNotFoundException){
            logger.info(e.message)
            exitProcess(1)
        }catch (e: ConflictException){
            logger.info(e.message)
            exitProcess(1)
        }catch (e: NoSuchElementException){
            logger.info("There is no IntegrationConfig file found in path")
            exitProcess(1)
        }
        finally {
            //House Cleaning goes here
        }
    }

    private fun parseVolumes(config: VolumeConfiguration){}
    private fun parseNetworks(config: NetworkConfiguration){}
    private fun parseContainers(config: NetworkConfiguration){}
}