package com.inspiringteam.reactivecompass.utils

import java.text.DecimalFormat
import java.text.NumberFormat

object FormatUtils {
    fun getStringFromDouble(d: Double): String {
        val formatter = DecimalFormat("###.#######")
        return formatter.format(d)
    }
}
