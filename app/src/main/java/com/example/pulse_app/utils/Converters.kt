package com.example.pulse_app.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("hh:mm")
    return formatter.format(Date(millis))
}

fun convertLocalDateTimeToDate(localDateTime: LocalDateTime): String{
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date.from(localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
}