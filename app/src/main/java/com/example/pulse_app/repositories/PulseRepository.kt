package com.example.pulse_app.repositories

import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.database.PulseDao
import kotlinx.coroutines.flow.Flow

class PulseRepository(
    private val pulseDao: PulseDao
) {
    fun getPulseHistory(): Flow<List<HistoryItem>> {
        return pulseDao.getPulseHistory()
    }

    suspend fun addNewRecord(item: HistoryItem) {
        pulseDao.addNewRecord(item)
    }
}