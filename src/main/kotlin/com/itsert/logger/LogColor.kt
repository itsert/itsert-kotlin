package com.itsert.logger

import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute

enum class LogColor{
   GREEN, YELLOW, BLUE, MAGENTA, CYAN,
     BRIGHT_GREEN, BRIGHT_YELLOW,
    BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN;

    fun toColorFormat(): AnsiFormat {
        return when(this){
            GREEN -> AnsiFormat(Attribute.GREEN_TEXT())
            YELLOW -> AnsiFormat(Attribute.YELLOW_TEXT())
            BLUE -> AnsiFormat(Attribute.BLUE_TEXT())
            MAGENTA -> AnsiFormat(Attribute.MAGENTA_TEXT())
            CYAN -> AnsiFormat(Attribute.CYAN_TEXT())
            BRIGHT_GREEN -> AnsiFormat(Attribute.BRIGHT_GREEN_TEXT())
            BRIGHT_YELLOW -> AnsiFormat(Attribute.BRIGHT_YELLOW_TEXT())
            BRIGHT_BLUE -> AnsiFormat(Attribute.BRIGHT_BLUE_TEXT())
            BRIGHT_MAGENTA -> AnsiFormat(Attribute.BRIGHT_MAGENTA_TEXT())
            BRIGHT_CYAN -> AnsiFormat(Attribute.BRIGHT_CYAN_TEXT())
        }
    }

    companion object {
        private val VALUES = values()
        private val SIZE = VALUES.size
        fun randomColor() = VALUES[(0 until SIZE).shuffled().first()]
    }
}