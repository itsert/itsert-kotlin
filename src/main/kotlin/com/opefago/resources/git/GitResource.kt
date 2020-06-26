package com.opefago.resources.git

import com.opefago.configuration.Configuration
import com.opefago.resources.Resource
import org.eclipse.jgit.api.Git
import java.io.File

class GitResource : Resource {
    private lateinit var git: Git
    override fun init(config: Configuration) {
        val gitFile = File(config.src)
        if(!gitFile.exists()){
            val git = Git.cloneRepository()
                    .setURI(config.src)
                    .setDirectory(gitFile)
                    .call()
        }else{

        }
    }

    override fun pull() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}