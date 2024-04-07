package com.example.pulse_app.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pulse_app.appContainer
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.repositories.PulseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val pulseRepository: PulseRepository
) : ViewModel() {

    var pulseHistory: List<HistoryItem>? = null

    init {
        viewModelScope.launch {
            pulseHistory = pulseRepository.getPulseHistory()
        }
    }

    fun addNewRecord(historyItem: HistoryItem) {
        viewModelScope.launch {
            pulseRepository.addNewRecord(historyItem)
            pulseHistory = pulseRepository.getPulseHistory()
        }
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