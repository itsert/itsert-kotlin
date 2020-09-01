package com.itsert.core.timer

import com.itsert.utils.CPUUtils

class Timer {
    companion object {
        fun functionRuntime(callback: () -> Unit): Long {
            val start = CPUUtils.getSystemTime()
            callback()
            val end = CPUUtils.getSystemTime()
            return (end - start)
        }
    }
}