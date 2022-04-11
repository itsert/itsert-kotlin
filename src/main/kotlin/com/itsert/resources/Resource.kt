package com.itsert.resources

import com.itsert.resources.container.Container

interface Resource{
    fun pull()
    fun build() : Container
    fun delete()
}