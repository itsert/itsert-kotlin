package com.itsert.utils

import java.lang.management.ManagementFactory

class CPUUtils {
    companion object{
        fun getCpuTime() : Long{
            val bean = ManagementFactory.getThreadMXBean()
            return if(bean.isCurrentThreadCpuTimeSupported){
                bean.currentThreadCpuTime
            }else{
                System.nanoTime()
            }
        }

        fun getUserTime() : Long{
            val bean = ManagementFactory.getThreadMXBean()
            return if(bean.isCurrentThreadCpuTimeSupported){
                bean.currentThreadUserTime
            }else{
                System.nanoTime()
            }
        }

        fun getSystemTime() : Long{
            val bean = ManagementFactory.getThreadMXBean()
            return if(bean.isCurrentThreadCpuTimeSupported){
                bean.currentThreadCpuTime - bean.currentThreadUserTime
            }else{
                System.nanoTime()
            }
        }
    }
}