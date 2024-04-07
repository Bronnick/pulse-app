package com.example.pulse_app.ui.theme.composables.history_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.view_models.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    val history by viewModel.pulseHistory.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        history.forEach { item ->
            HistoryItemView(pulseData = item)
        }
    }
}