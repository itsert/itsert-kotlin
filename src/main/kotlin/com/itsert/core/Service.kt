package com.itsert.core

import com.itsert.configuration.ServiceConfiguration
import com.itsert.utils.Utils
import com.itsert.utils.Utils.Companion.NAMESPACE
import com.itsert.utils.Utils.Companion.splitPortPair
import io.fabric8.kubernetes.api.model.*
import io.fabric8.kubernetes.api.model.apps.Deployment
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder
import io.fabric8.kubernetes.client.DefaultKubernetesClient
import io.fabric8.kubernetes.client.KubernetesClient
import java.util.*


class Service private constructor(
        private val client: KubernetesClient,
        private val config: ServiceConfiguration,
        private val deployment: Deployment,
        private val service: io.fabric8.kubernetes.api.model.Service
) {
    companion object{
        private const val SERVICE_TYPE = "NodePort"
        fun create(config: ServiceConfiguration): Service{
            val client: KubernetesClient = DefaultKubernetesClient()
            val deployment = createDeployment(config)
            val service = createService(config)
            return Service(client, config, deployment, service)
        }

        private fun createService(config: ServiceConfiguration): io.fabric8.kubernetes.api.model.Service{
            val servicePorts: MutableList<ServicePort> = ArrayList()
            config.ports?.forEach {
                val servicePort = ServicePort()
                val ports = splitPortPair(it)

                servicePort.name = "http"
                servicePort.port = ports.first.toInt()
                servicePort.targetPort = IntOrString(ports.second.toInt())
                servicePorts.add(servicePort)
            }
            return ServiceBuilder()
                    .withNewMetadata()
                    .withNamespace(NAMESPACE)
                    .withName("${config.name}-service")
                    .withLabels(mapOf("app" to  config.name))
                    .endMetadata()
                    .withNewSpec()
                    .withType(SERVICE_TYPE)
                    .withPorts(servicePorts)
                    .withSelector(mapOf("app" to config.name))
                    .endSpec()
                    .withNewStatus()
                    .endStatus().build()

        }

        private fun populateEnvironmentVariables(config: ServiceConfiguration): List<EnvVar>{
            val envVars = mutableListOf<EnvVar>()
            config.environment?.forEach {
                val envVar = EnvVar()
                val pair = Utils.splitEnvPair(it)
                envVar.name = pair.first
                envVar.value = pair.second
                envVars.add(envVar)
            }
            return envVars
        }
        private fun createDeployment(config: ServiceConfiguration): Deployment{
            val containerPorts = mutableListOf<ContainerPort>()
            config.ports?.forEach {
                val containerPort = ContainerPort()
                val ports = splitPortPair(it)

                containerPort.name = "http"
                containerPort.containerPort = ports.second.toInt()
                containerPort.protocol = "TCP"
                containerPorts.add(containerPort)
            }
            val envVars = populateEnvironmentVariables(config)
            return DeploymentBuilder()
                    .withNewMetadata()
                    .withName(config.name)
                    .withLabels(mapOf("app" to config.name))
                    .endMetadata()
                    .withNewSpec()
                    .withNewSelector()
                    .withMatchLabels(mapOf("app" to config.name))
                    .endSelector()
                    .withReplicas(config.replica)
                    .withNewTemplate()
                    .withNewMetadata()
                    .addToLabels("app", config.name)
                    .endMetadata()
                    .withNewSpec()
                    .addNewContainer()
                    .withName(config.name)
                    .withImage(config.image)
                    .withEnv(envVars)
                    .withPorts(containerPorts)
                    .endContainer()
                    .endSpec()
                    .endTemplate()
                    .endSpec()
                    .build()
        }
    }

    init {
        client.apps().deployments().inNamespace(NAMESPACE).createOrReplace(deployment)
        client.services().inNamespace(NAMESPACE).createOrReplace(service)
    }

    fun start(){
        client.apps()
                .deployments()
                .inNamespace(NAMESPACE)
                .withName(config.name)
                .scale(config.replica, true)
    }

    fun stop(){
        client.apps()
                .deployments()
                .inNamespace(NAMESPACE)
                .withName(config.name)
                .scale(0, true)
    }

    fun delete(){
        client.services().inNamespace(NAMESPACE).delete(service)
        client.apps().deployments().inNamespace(NAMESPACE).delete(deployment)
    }
}