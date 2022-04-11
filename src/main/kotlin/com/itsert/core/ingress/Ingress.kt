package com.itsert.core.ingress

import io.fabric8.kubernetes.api.model.HasMetadata
import io.fabric8.kubernetes.client.DefaultKubernetesClient
import io.fabric8.kubernetes.client.KubernetesClient

class Ingress {
    val client: KubernetesClient = DefaultKubernetesClient()

    fun run(){

//        val factory = client.informers().addSharedInformerEventListener {i }
        val ingressList = client.extensions().ingresses().list()
        val serviceList = client.services().list()

        ingressList.items.forEach { it.spec.tls.forEach {  } }
        val secretList = client.secrets().list()
    }
}