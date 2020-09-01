package com.itsert.logger

import com.diogonunes.jcolor.Ansi
import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute.*
import com.itsert.logger.error.ErrorLevel


class ILogger {
    companion object{
        fun log(message: String, error: ErrorLevel = ErrorLevel.INFO){
            val fWarning = AnsiFormat(YELLOW_TEXT(), BLUE_BACK(), BOLD())
            val fInfo = AnsiFormat(GREEN_TEXT())
            val fError = AnsiFormat(RED_TEXT())
            when(error){
                ErrorLevel.INFO->{
                    println(Ansi.colorize(message, fInfo))}
                ErrorLevel.WARNING->{Ansi.colorize(message, fWarning)}
                ErrorLevel.ERROR->{Ansi.colorize(message, fError)}
            }
        }

        fun log(message: Any, error: ErrorLevel = ErrorLevel.INFO){
            log(message.toString(), error)
        }
    }
}