package com.itsert.logger

import com.diogonunes.jcolor.Ansi
import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
import kotlin.math.abs


enum class LogColor{
    BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, WHITE, CYAN;

    fun toColorFormat(): AnsiFormat{
        return when(this){
            BLACK-> AnsiFormat(Attribute.BLACK_TEXT())
            RED -> AnsiFormat(Attribute.RED_TEXT())
            GREEN -> AnsiFormat(Attribute.GREEN_TEXT())
            YELLOW -> AnsiFormat(Attribute.YELLOW_TEXT())
            BLUE -> AnsiFormat(Attribute.BLUE_TEXT())
            MAGENTA -> AnsiFormat(Attribute.MAGENTA_TEXT())
            WHITE -> AnsiFormat(Attribute.WHITE_TEXT())
            CYAN -> AnsiFormat(Attribute.CYAN_TEXT())
        }
    }
    companion object {
        private val VALUES = values()
        private val SIZE = VALUES.size
        fun randomColor() = VALUES[(0 until SIZE).shuffled().first()]
    }
}
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