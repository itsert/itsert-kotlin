package com.opefago.resources

import com.opefago.configuration.Configuration

interface Resource {
    fun init(config: Configuration)
    fun pull()
    fun run()
    fun delete()
}