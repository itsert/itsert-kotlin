package com.itsert

import com.itsert.core.Project
import com.itsert.utils.Utils


fun main(args: Array<String>){
    println("Hello world!!")
    println(Utils.gitNameFromUrl("https://github.com/docker-java/docker-java.git"))
    Project.instance.run()
}