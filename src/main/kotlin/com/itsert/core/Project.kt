package com.itsert.core

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.itsert.configuration.ConfigParser
import com.itsert.configuration.NetworkConfiguration
import com.itsert.configuration.ServiceConfiguration
import com.itsert.core.scriptengine.parser.ITDLLexer
import com.itsert.core.scriptengine.parser.ITDLParser
import com.itsert.core.scriptengine.visitor.Interpreter
import com.itsert.exceptions.ImageNotFoundException
import com.itsert.resources.container.Container
import com.itsert.resources.docker.DockerResource
import com.itsert.resources.network.Network
import com.itsert.resources.volume.Volume
import com.itsert.utils.FileUtils
import mu.KLogging
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import java.io.File
import java.io.IOException
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
    private lateinit var defaultNetwork: Network
    fun getContainers(): Map<String, Container> = containers

    fun run(){
        runAppConfigs()

        runScripts()
        try {


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

    private fun runAppConfigs() {
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

            logger.info("Creating Default Network")
            createDefaultNetwork(dockerClient)

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
    }

    private fun createDefaultNetwork(client: DockerClient){
        defaultNetwork = Network.create(client, NetworkConfiguration(
                name = "default_integration"
        ))
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
            }else{
                defaultNetwork.connect(cont.id())
            }
            containers[it.name] = cont
        }
    }

    private fun runScripts(){
        FileUtils.walkFilesAndFilesSubDirectories(".", listOf("itdl")) {
            printFileDemarcation(it)
            var cs: CharStream? = null //load the file
            try {
                cs = CharStreams.fromFileName(it)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val lexer = ITDLLexer(cs)
            val stream = CommonTokenStream(lexer)
            val parser = ITDLParser(stream)
            val tree: ParseTree = parser.script() // parse the content and get the tree

            val interpreter = Interpreter()
            interpreter.visit(tree)
        }
    }

    private fun printFileDemarcation(filename: String){
        println(filename)
    }
}