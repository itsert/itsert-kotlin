package com.opefago.resources.vcs

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.model.BuildResponseItem
import com.opefago.configuration.ServiceConfiguration
import com.opefago.exceptions.BuildFailedException
import com.opefago.exceptions.InvalidDockerProjectException
import com.opefago.resources.Resource
import com.opefago.resources.container.Container
import com.opefago.utils.Utils
import mu.KLogging
import org.eclipse.jgit.api.Git
import java.io.File

class GitResource(
        private val client: DockerClient,
        private val config: ServiceConfiguration) : Resource {
    private lateinit var gitFile: File
    companion object : KLogging()

    override fun pull(){
        val gitFile = File(Utils.gitNameFromUrl(config.repository!!))
        if(!gitFile.exists()){
            Git.cloneRepository()
                    .setURI(config.repository)
                    .setDirectory(gitFile)
                    .call()
        }
    }

    override fun build(): Container {
        if(!gitFile.exists()){
            throw BuildFailedException("Cannot find path for ${config.repository}, something went wrong")
        }
        val folderName = Utils.gitNameFromUrl(config.repository!!)
        val file = File(folderName + System.lineSeparator() + "Dockerfile")
        if(file.exists()) {
            client.buildImageCmd(gitFile)
                    .withTags(setOf(config.tag ?: "latest"))
                    .withBuildArg("name", config.name)
                    .exec(object : ResultCallback.Adapter<BuildResponseItem>() {
                        override fun onError(throwable: Throwable?) {
                            super.onError(throwable)
                            logger.info("Problem encountered will trying to build git repository")
                            throw BuildFailedException(throwable?.message)
                        }
                    }).awaitCompletion()
        }else{
            throw InvalidDockerProjectException("Invalid: Dockerfile not found in project root")
        }
        return Container.create(client, config)
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}