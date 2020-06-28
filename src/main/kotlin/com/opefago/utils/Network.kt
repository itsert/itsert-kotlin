package com.opefago.utils

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.CreateNetworkCmd
import com.opefago.configuration.NetworkConfiguration
import com.opefago.exceptions.InvalidPairException
import com.github.dockerjava.api.model.Network as GitNetwork

class Network(
        private var client: DockerClient,
              private var config: NetworkConfiguration) {
    private val networkId: String
    init {
        val network = createNetworkCmd(client, config)
        networkId = network.exec().id
    }

    private fun createNetworkCmd(client: DockerClient, config: NetworkConfiguration)
            : CreateNetworkCmd {
        val network = client.createNetworkCmd()
        network.withDriver(config.driver)
        network.withInternal(config.internal)
        network.withAttachable(config.attachable)
        network.withEnableIpv6(config.enableIpv6)
        network.withCheckDuplicate(true)
        val ipam = GitNetwork.Ipam()
        ipam.withDriver(config.ipam.driver)
        val configs = config.ipam.config?.map {
            GitNetwork.Ipam.Config()
                    .withGateway(it.gateway)
                    .withIpRange(it.ipRange)
                    .withSubnet(it.subnet)
        }
        ipam.withDriver(config.ipam.driver)
        ipam.withConfig(configs)
        network.withIpam(ipam)
        val options = config.options.associateBy( {splitPair(it)[0]}, {splitPair(it)[1]})
        network.withOptions(options)
        return network
    }

    fun connect(containerId: String){
        client.connectToNetworkCmd().withContainerId(containerId)
                .withNetworkId(networkId)
                .exec()
    }

    private fun splitPair(string: String): List<String>{
        val pair = string.split(":(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)")
//        val pair = string.split("\"[^\"]*\"|[^:]+")
        if(pair.size != 2){
            throw InvalidPairException("Invalid key/value pair")
        }
        return pair
    }

}