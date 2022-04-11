package com.itsert.utils

import java.io.File


class FileUtils {
    companion object{
        fun walkFilesAndFilesSubDirectories(
                directoryName: String,
                filterByFileExtension: List<String> = listOf(),
                callback: (file: String) -> Unit
        ){
            val directory = File(directoryName)
            val subDirectoriesInPath = mutableListOf<String>()
            val fileList = directory.listFiles {
                file, filename ->
                filterByFileExtension.isEmpty() ||
                        file.length() > 0 &&
                        filterByFileExtension.any { filename.toLowerCase().endsWith(it.toLowerCase()) }
            }
            fileList?.forEach {
                if (it.isFile) {
                    callback(it.absolutePath)
                } else if (it.isDirectory) {
                    subDirectoriesInPath.add(it.absolutePath)
                }
            }
            subDirectoriesInPath.forEach {
                walkFilesAndFilesSubDirectories(it, filterByFileExtension, callback)
            }
        }
    }
}