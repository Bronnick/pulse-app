package com.example.pulse_app.classes

import java.time.LocalDateTime

data class HistoryItem (
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val localDateTime: LocalDateTime,
    val pulse: Int
    )