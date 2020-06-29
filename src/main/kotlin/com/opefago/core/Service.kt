//package com.opefago.core
//
//import com.github.dockerjava.api.DockerClient
//import com.github.dockerjava.api.async.ResultCallback
//import com.github.dockerjava.api.model.*
//import com.opefago.configuration.ServiceConfiguration
//import com.opefago.resources.container.Container
//
//class Service(
//        private val client: DockerClient,
//        private val serviceConfiguration: ServiceConfiguration
//){
//    private val serviceId: String
//    private val containers : List<Container>
//    init {
////        val replicas = ServiceReplicatedModeOptions()
////                .withReplicas(serviceConfiguration.replica?:1)
////        val mode = ServiceModeConfig().withReplicated(replicas)
////        val spec = ServiceSpec()
////                .withName(serviceConfiguration.name)
////                .withMode(mode)
////        if(serviceConfiguration.networks != null){
////            spec.withNetworks(serviceConfiguration.networks.map { NetworkAttachmentConfig().withTarget(it) })
////        }else{
////
////        }
////        serviceId = client.createServiceCmd(spec).exec().id
////        client.logServiceCmd(serviceId).withStdout(true)
////                .withStderr(true)
////                .exec(object : ResultCallback.Adapter<Frame?>() {
////                    @Override
////                    fun onNext(frame: Frame) {
////                        Container.logger.info(frame.payload.toString())
////                    }
////                })
////        containers = client.listContainersCmd()
////                .withNameFilter(listOf(serviceConfiguration.name))
////                .exec()
////                . map { Container.createFromId(client, it.id) }
//    }
//    fun start(){
//        containers.forEach {
//            it.start()
//        }
//    }
//
//    fun stop(){
//        containers.forEach {
//            it.stop()
//        }
//    }
//    fun updateService(){
//        val spec = ServiceSpec()
//        client.updateServiceCmd(serviceId, spec).exec()
//    }
//    fun delete(){
//        client.removeServiceCmd(serviceId).exec()
//    }
//}