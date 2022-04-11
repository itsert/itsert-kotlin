package com.itsert.logger

import com.diogonunes.jcolor.Ansi
import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute.*
import com.itsert.logger.error.ErrorLevel
import java.io.OutputStream
import java.io.PrintStream
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import kotlin.math.abs


class ILogger(
        private val outputStream: OutputStream
): PrintStream(outputStream){
    companion object{
        fun log(message: String, error: ErrorLevel = ErrorLevel.DEFAULT){
            val fWarning = AnsiFormat(YELLOW_TEXT(), BLUE_BACK(), BOLD())
            val fInfo = AnsiFormat(BRIGHT_GREEN_TEXT())
            val fError = AnsiFormat(RED_TEXT())
            val fHeader= AnsiFormat(BRIGHT_BLUE_TEXT())
            when(error){
                ErrorLevel.INFO->{ println(Ansi.colorize(message, fInfo))}
                ErrorLevel.DEFAULT->{println((message))}
                ErrorLevel.WARNING->{println(Ansi.colorize(message, fWarning))}
                ErrorLevel.ERROR->{println(Ansi.colorize(message, fError))}
                ErrorLevel.HEADING->{println(Ansi.colorize(message, fHeader))}
            }
        }
    }
}