package com.example.pulse_app.ui.theme.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pulse_app.R
import com.example.pulse_app.ui.theme.composables.history_screen.HistoryScreen
import com.example.pulse_app.ui.theme.composables.main_screen.MainScreen
import com.example.pulse_app.ui.theme.composables.new_record_screen.NewRecordScreen
import com.example.pulse_app.utils.convertLocalDateTimeToDate
import com.example.pulse_app.view_models.HistoryViewModel
import java.time.LocalDateTime

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int
) {
    data object MainScreen : Screen(
        route = "main",
        resourceId = R.string.main
    )

    data object HistoryScreen : Screen(
        route = "history",
        resourceId = R.string.history
    )

    data object NewRecordScreen : Screen(
        route = "new_record",
        resourceId = R.string.new_record
    )
}

/*
val testHistoryList = listOf(
    HistoryItem(
        1,
        systolicPressure = 100,
        diastolicPressure = 100,
        localDateTime = LocalDateTime.now().toString(),
        pulse = 100
    ),
    HistoryItem(
        1,
        systolicPressure = 100,
        diastolicPressure = 100,
        localDateTime = LocalDateTime.now().toString(),
        pulse = 100
    ),
    HistoryItem(
        1,
        systolicPressure = 100,
        diastolicPressure = 100,
        localDateTime = LocalDateTime.now().toString(),
        pulse = 100
    ),
    HistoryItem(
        1,
        systolicPressure = 100,
        diastolicPressure = 100,
        localDateTime = LocalDateTime.now().toString(),
        pulse = 100
    ),
    HistoryItem(
        1,
        systolicPressure = 100,
        diastolicPressure = 100,
        localDateTime = LocalDateTime.now().toString(),
        pulse = 100
    ),
)
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseApp() {

    val historyViewModel: HistoryViewModel =
        viewModel(factory = HistoryViewModel.Factory)

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val surfaceColor = if(isSystemInDarkTheme()) Color(0xFF383A37) else Color(0xFFE7E7E7)

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(color = surfaceColor),
        topBar = {
            val text =
                when(navBackStackEntry?.destination?.route) {
                    "history" -> "History"
                    "main" -> "Blood Pressure BPM Tracker"
                    "new_record" -> "New Record"
                    else -> ""
                }
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                    //.background(color = MaterialTheme.colorScheme.primaryContainer),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0, 128, 104)
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                            //.background(color = MaterialTheme.colorScheme.secondary),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(navBackStackEntry?.destination?.route == "history" || navBackStackEntry?.destination?.route == "new_record") {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    tint = Color.White,
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = text,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
            })

        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen.route,
            modifier = Modifier.padding(it)
        ) {
            composable(
                route = Screen.MainScreen.route,
            ) {
                MainScreen(
                    viewModel = historyViewModel,
                    onViewAllHistoryButtonClick = {
                        navController.navigate(Screen.HistoryScreen.route)
                    },
                    onAddRecordButtonClick = {
                        historyViewModel.setDateValue(convertLocalDateTimeToDate(LocalDateTime.now()))
                        historyViewModel.setTimeValue(LocalDateTime.now().toString().drop(11).take(5))
                        navController.navigate(Screen.NewRecordScreen.route)
                    }
                )
            }

            composable(
                route = Screen.HistoryScreen.route
            ) {
                HistoryScreen(
                    viewModel = historyViewModel
                )
            }

            composable(
                route = Screen.NewRecordScreen.route
            ) {
                NewRecordScreen(
                    viewModel = historyViewModel,
                    onSaveButtonClick = { historyItem ->
                        historyViewModel.addNewRecord(historyItem)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}