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
import com.opefago.configuration.ServiceConfiguration
import com.opefago.exceptions.ImageNotFoundException
import com.opefago.resources.container.Container
import com.opefago.resources.docker.DockerResource
import com.opefago.resources.network.Network
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
    private lateinit var containers: MutableMap<String, Container>
    private lateinit var network: MutableMap<String, Network>
    private lateinit var volumes: MutableMap<String, Volume>
    fun run(){
        try {
            val parser = ConfigParser()
            val path = INTEGRATION_CONFIGS.first {
                File(it).exists()
            }
            val file = File(path)
            val configuration = parser.parse(file)
            containers = mutableMapOf()
            network = mutableMapOf()
            volumes = mutableMapOf()
            if(configuration.networks != null){
                logger.info("Creating Network")
                val networkConfigs = configuration.networks.map {
                    it.value.withName(it.key)
                }
                processNetworks(dockerClient, networkConfigs)
            }

            if(configuration.services != null) {
                val serviceConfigs = configuration.services.map {
                    it.value.withName(it.key)
                }

                buildContainers(dockerClient, serviceConfigs)
            }
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

//    private fun parseVolumes(configs: List<VolumeConfiguration>){
//    }
    private fun processNetworks(client: DockerClient, configs: List<NetworkConfiguration>){
        configs.forEach {
            val net = Network.create(client, it)
            network[it.name] = net

        }
    }

    private fun buildContainers(client: DockerClient, configs: List<ServiceConfiguration>){
        configs.forEach {
            val dockerResource = DockerResource(client, it)
            logger.info("pulling Image")
            dockerResource.pull()

            logger.info("Building Container")
            val cont = dockerResource.build()
            if(it.networks != null){
                it.networks.forEach {networkIt->
                    val network = network[networkIt]
                    network?.connect(cont.id())
                }
            }
            containers[it.name] = cont
        }
    }
}