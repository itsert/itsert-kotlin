package com.opefago

import com.opefago.core.Project
import com.opefago.utils.Utils


fun main(args: Array<String>){
    println("Hello world!!")
    println(Utils.gitNameFromUrl("https://github.com/docker-java/docker-java.git"))
    Project.instance.run()
}