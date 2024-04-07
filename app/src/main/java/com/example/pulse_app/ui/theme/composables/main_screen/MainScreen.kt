package com.example.pulse_app.ui.theme.composables.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pulse_app.R
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.ui.theme.composables.history_screen.HistoryItemView
import com.example.pulse_app.view_models.HistoryViewModel

@Composable
fun MainScreen(
    viewModel: HistoryViewModel,
    onViewAllHistoryButtonClick: () -> Unit,
    onAddRecordButtonClick: () -> Unit
) {
    val history by remember {

        mutableStateOf(viewModel.pulseHistory)
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        history?.take(3)?.forEach { item ->
            HistoryItemView(pulseData = item)
        }

        if((history?.size ?: 0) > 3) {
            AllHistoryButton(
                onViewAllHistoryButtonClick = onViewAllHistoryButtonClick
            )
        }

        Box(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(onClick = onAddRecordButtonClick) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun AllHistoryButton(
    onViewAllHistoryButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(all = 16.dp)
            .clickable {
                onViewAllHistoryButtonClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_history_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "All History")
        }
    }
}