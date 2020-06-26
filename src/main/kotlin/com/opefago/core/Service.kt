package com.opefago.core

import com.opefago.utils.Container

class Service {
    private lateinit var container: Container
    get

    fun init(container: Container){
        this.container = container
    }
    fun start(){}
    fun stop(){}
    fun getContainer() = container
}