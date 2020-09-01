package com.itsert.resources.network

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.CreateNetworkCmd
import com.itsert.configuration.NetworkConfiguration
import com.itsert.utils.Utils
import mu.KLogging
import com.github.dockerjava.api.model.Network as GitNetwork

class Network private constructor(
        private val client: DockerClient,
        private val networkId: String
) {

    companion object : KLogging(){
        fun create(client: DockerClient, config: NetworkConfiguration): Network{
            val foundNetwork = client
                    .listNetworksCmd()
                    .withNameFilter(config.name)
                    .exec()

            if(foundNetwork.isNotEmpty()){
                return Network(client, foundNetwork[0].id)
            }
            val network = createNetworkCmd(client, config)
            val networkId = network.exec().id
            return Network(client, networkId)
        }

        private fun createNetworkCmd(client: DockerClient, config: NetworkConfiguration)
                : CreateNetworkCmd {
            val network = client.createNetworkCmd()
            network.withDriver(config.driver)
            network.withInternal(config.internal)
            network.withAttachable(config.attachable)
            network.withEnableIpv6(config.enableIpv6)
            network.withCheckDuplicate(true)
            network.withName(config.name)
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
            val options = config.options.associateBy( {Utils.splitPair(it).first}, {Utils.splitPair(it).second})
            network.withOptions(options)
            return network
        }
    }

    fun connect(containerId: String){
        client.connectToNetworkCmd().withContainerId(containerId)
                .withNetworkId(networkId)
                .exec()
    }

    fun delete(){
        client.removeNetworkCmd(networkId).exec()
    }

}