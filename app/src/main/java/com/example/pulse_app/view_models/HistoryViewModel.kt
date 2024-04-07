package com.example.pulse_app.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pulse_app.appContainer
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.repositories.PulseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class HistoryViewModel(
    private val pulseRepository: PulseRepository
) : ViewModel() {

    val pulseHistory: Flow<List<HistoryItem>>
        = pulseRepository.getPulseHistory()

    var diastolicPressure by mutableIntStateOf(0)
        private set

    var systolicPressure by mutableIntStateOf(0)
        private set

    var pulse by mutableIntStateOf(0)
        private set

    var date by mutableStateOf(LocalDateTime.now().toString().take(10))
        private set

    var time by mutableStateOf(LocalDateTime.now().toString().drop(11).take(5))
        private set

    init {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        date = formatter.format(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
    }

    fun addNewRecord(historyItem: HistoryItem) {
        viewModelScope.launch {
            pulseRepository.addNewRecord(historyItem)
            //pulseHistory = pulseRepository.getPulseHistory()
        }
    }

    fun setDiastolic(newValue: Int) {
        diastolicPressure = newValue
    }

    fun setSystolic(newValue: Int) {
        systolicPressure = newValue
    }

    fun setPulseValue(newValue: Int) {
        pulse = newValue
    }

    fun setDateValue(newValue: String) {
        date = newValue
    }

    fun setTimeValue(newValue: String) {
        time = newValue
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val pulseRepository = appContainer.pulseRepository
                HistoryViewModel(pulseRepository)
            }
        }
    }
}