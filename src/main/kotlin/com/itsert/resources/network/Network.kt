package com.itsert.resources.network

import com.itsert.configuration.ServiceConfiguration
import com.itsert.utils.Utils.Companion.DEFAULT_NETWORK
import com.itsert.utils.Utils.Companion.NAMESPACE
import com.itsert.utils.Utils.Companion.splitPortPair
import io.fabric8.kubernetes.api.model.IntOrString
import io.fabric8.kubernetes.api.model.extensions.*
import io.fabric8.kubernetes.client.KubernetesClient
import mu.KLogging


class Network private constructor(
        private val client: KubernetesClient,
        private val ingress: Ingress
) {
    companion object : KLogging(){
        fun create(client: KubernetesClient, config: Map<String, ServiceConfiguration>): Network{
            val ingress = createIngress(config)
            return Network(client, ingress)
        }

        private fun createIngress(config: Map<String, ServiceConfiguration>): Ingress {
            val httpIngressRule = HTTPIngressRuleValue()
            val httpIngressPaths = mutableListOf<HTTPIngressPath>()
            config.forEach{
                val ingressBackend = IngressBackend()
                ingressBackend.serviceName =  "${it.key}-service"
                ingressBackend.servicePort = IntOrString(splitPortPair(it.value.ports!![0]).first.toInt())
                val httpIngressPath = HTTPIngressPath()
                httpIngressPath.backend = ingressBackend
                httpIngressPath.path = "/${it.key}"
                httpIngressPaths.add(httpIngressPath)
            }

            httpIngressRule.paths = httpIngressPaths

            val ingressRule = IngressRule()
            ingressRule.http = httpIngressRule

            return IngressBuilder()
                    .withNewMetadata()
                    .withName(DEFAULT_NETWORK)
                    .endMetadata()
                    .withNewSpec()
                    .withRules(listOf(ingressRule))
                    .endSpec()
                    .build()
        }
    }

    fun connect() : Boolean{
        client.extensions().ingresses().inNamespace(NAMESPACE).createOrReplace(ingress)
        return true
    }

    fun delete(){
        client.extensions().ingresses().inNamespace(NAMESPACE).delete(ingress)
    }

}