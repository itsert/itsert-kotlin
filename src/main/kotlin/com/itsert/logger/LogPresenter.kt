package com.itsert.logger

import com.diogonunes.jcolor.Ansi
import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
import kotlin.math.abs

class LogPresenter(
        private val podName: String,
        private val prefixWidth: Int,
        private val color: LogColor,
        private val outputStream: OutputStream
): PrintStream(outputStream) {
    override fun write(buf: ByteArray, off: Int, len: Int) {
        var output = "${addPadding(podName, prefixWidth)}|${String(buf)}"
        val colorFormat =  color.toColorFormat()
        output = Ansi.colorize(output, colorFormat)
        outputStream.write(output.toByteArray(), 0, output.toByteArray().size)
    }

    override fun flush() {
        outputStream.flush()
        super.flush()
    }

    private fun addPadding(name:String, length:Int): String{
        val str = StringBuilder(name)
        var idx = abs(str.length - length)
        for (i in 0..idx){
            str.append(" ")
        }
        return str.toString()
    }
}