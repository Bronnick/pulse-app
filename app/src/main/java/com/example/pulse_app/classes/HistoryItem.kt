package com.example.pulse_app.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "pulse_data")
data class HistoryItem (

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name="systolic")
    val systolicPressure: Int,

    @ColumnInfo(name="diastolic")
    val diastolicPressure: Int,

    @ColumnInfo(name="time")
    val localDateTime: String,

    @ColumnInfo(name="pulse")
    val pulse: Int
)