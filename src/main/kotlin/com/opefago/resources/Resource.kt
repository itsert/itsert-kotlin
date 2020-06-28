package com.opefago.resources

import com.opefago.utils.Container

interface Resource{
    fun pull()
    fun build() : Container
    fun delete()
}