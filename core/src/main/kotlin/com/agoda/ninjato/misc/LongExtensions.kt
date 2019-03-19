package com.agoda.ninjato.misc

import java.text.SimpleDateFormat
import java.util.*

internal fun Long.toUTCDateTime() = SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz").also {
    it.timeZone = TimeZone.getTimeZone("UTC")
}.format(Date(this * 1000L))
