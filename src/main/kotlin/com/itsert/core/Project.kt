package com.itsert.core

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import com.itsert.configuration.ConfigParser
import com.itsert.configuration.ServiceConfiguration
import com.itsert.core.scriptengine.parser.ITDLLexer
import com.itsert.core.scriptengine.parser.ITDLParser
import com.itsert.core.scriptengine.visitor.Interpreter
import com.itsert.exceptions.ImageNotFoundException
import com.itsert.exceptions.RuntimeError
import com.itsert.logger.LogColor
import com.itsert.logger.LogPresenter
import com.itsert.resources.network.Network
import com.itsert.utils.FileUtils
import com.itsert.utils.Utils
import com.itsert.utils.Utils.Companion.DEFAULT_NETWORK
import com.itsert.utils.Utils.Companion.NAMESPACE
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.client.DefaultKubernetesClient
import io.fabric8.kubernetes.client.KubernetesClient
import mu.KLogging
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess


class Project private constructor(){
    private val dockerClient: DockerClient
    private val kubernetesClient: KubernetesClient
    private var services: MutableMap<String, Service>
    init {
        val standard = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .build()

        val httpClient: DockerHttpClient = ApacheDockerHttpClient.Builder()
                .dockerHost(standard.dockerHost)
                .sslConfig(standard.sslConfig)
                .build()
        dockerClient = DockerClientImpl.getInstance(standard, httpClient)
        kubernetesClient =  DefaultKubernetesClient()
        services = mutableMapOf()
    }

    private object HOLDER {
        val INSTANCE = Project()
    }

    companion object : KLogging() {
        val instance: Project by lazy { HOLDER.INSTANCE }
    }
    private lateinit var defaultNetwork: Network

    fun run() = try {
        runAppConfigs()
        runScripts()
    }
    finally {
        logger.info("Tearing down all resources")
        logger.info("Tearing down networks")
//        defaultNetwork.delete()
        logger.info("Tearing down services")
        services.forEach { (_, u) ->
//            u.delete()
        }
    }

    fun startServices(names: List<String>, token: Token): Int{
        logger.info("Starting services $names")
        names.forEach {
            if(services.containsKey(it))
//                services[it]?.start()
            else
                throw RuntimeError("$it is not a registered service", token)
        }
        return attachLoggerAndWait(kubernetesClient)
    }

    private fun getLongestNameLengthAndPodColor(client: KubernetesClient): Pair<Int, Map<String, LogColor>>{
        var nameLength = 0
        val colorMap = mutableMapOf<String, LogColor>()
        client.pods().inNamespace(NAMESPACE).list().items.forEach {
            if(it.metadata.name.length > nameLength){
                nameLength = it.metadata.name.length
            }
            val color = LogColor.randomColor()
            colorMap[it.metadata.name] = color
        }
        return Pair(nameLength, colorMap)
    }

    private fun attachLoggerAndWait(client: KubernetesClient): Int{
        var podProperties = getLongestNameLengthAndPodColor(client)
        client.pods().inNamespace(NAMESPACE).list().items.forEach {
            client.pods()
                    .inNamespace(NAMESPACE)
                    .withName(it.metadata.name)
                    .watchLog(
                            LogPresenter(it.metadata.name,
                                    podProperties.first + 2,
                                    podProperties.second[it.metadata.name] ?: LogColor.randomColor(),
                                    System.out
                            )
                    )
            client.pods()
                .inNamespace(NAMESPACE)
                .withName(it.metadata.name)
                .waitUntilCondition({ r: Pod -> r.status.phase == "Running"  }, 15, TimeUnit.MINUTES)
        }
        return podProperties.first
    }

    private fun runAppConfigs() {
        try {
            val parser = ConfigParser()
            val path = Utils.INTEGRATION_CONFIGS.first {
                File(it).exists()
            }
            val file = File(path)
            val configuration = parser.parse(file)

            logger.info("Creating Default Network")
            createDefaultNetwork(kubernetesClient,  configuration.services!!)

            val serviceConfigs = configuration.services.map {
                it.value.withName(it.key)
            }
            buildServices(serviceConfigs)
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

    private fun createDefaultNetwork(client: KubernetesClient, config: Map<String, ServiceConfiguration>){
        defaultNetwork = Network.create(client, config)
        defaultNetwork.connect()

        client.extensions().ingresses().inNamespace(NAMESPACE).withName(DEFAULT_NETWORK).fromServer().get().metadata.additionalProperties
    }

    private fun buildServices(configs: List<ServiceConfiguration>){
        logger.info("Building Services")
        configs.forEach {
            val svc = Service.create(it)
            services[it.name] = svc
        }
    }

    private fun runScripts(){
        FileUtils.walkFilesAndFilesSubDirectories(".", listOf("itdl")) {
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

            val interpreter = Interpreter(it.substring(it.lastIndexOf(File.separator) + 1))
            interpreter.visit(tree)
        }
    }
}