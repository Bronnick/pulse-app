package com.example.pulse_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pulse_app.classes.HistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PulseDao {

    @Query("SELECT * FROM pulse_data")
    fun getPulseHistory(): Flow<List<HistoryItem>>

    @Insert
    suspend fun addNewRecord(historyItem: HistoryItem)
}