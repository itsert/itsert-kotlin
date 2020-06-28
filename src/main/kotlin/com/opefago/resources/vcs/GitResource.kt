package com.opefago.resources.vcs

import com.opefago.configuration.ServiceConfiguration
import com.opefago.resources.Resource
import com.opefago.utils.Container
import mu.KLogging
import org.eclipse.jgit.api.Git
import java.io.File

class GitResource : Resource {
    private lateinit var gitFile: File
    private lateinit var config: ServiceConfiguration
    companion object : KLogging()

    override fun pull(){
        gitFile = File(config.repository)
        if(!gitFile.exists()){
            Git.cloneRepository()
                    .setURI(config.repository)
                    .setDirectory(gitFile)
                    .call()
        }else{

        }
    }

    override fun build(): Container {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}