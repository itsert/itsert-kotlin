package com.opefago.utils

class Container {


    fun Id(){}
    fun name(){}
    fun port(){}
    fun isRunning(): Boolean{
        return false
    }
    fun isRestarting(): Boolean{
        return false
    }

    fun isPaused(): Boolean{
        return false
    }

    fun kill(){}
    fun restart(){}
    fun unpause(){}
    fun remove(){}
    fun create_exec(){}
    fun start_exec(){}
    fun create(){}
    fun start(){}
    fun stop(){}
    fun delete(){}
}