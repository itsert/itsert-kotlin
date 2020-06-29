package com.opefago.resources

import com.opefago.resources.container.Container

interface Resource{
    fun pull()
    fun build() : Container
    fun delete()
}