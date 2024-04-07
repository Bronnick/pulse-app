package com.example.pulse_app.ui.theme.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pulse_app.R
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.ui.theme.composables.history_screen.HistoryScreen
import com.example.pulse_app.ui.theme.composables.main_screen.MainScreen
import com.example.pulse_app.ui.theme.composables.new_record_screen.NewRecordScreen
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseApp() {

    val historyViewModel: HistoryViewModel =
        viewModel(factory = HistoryViewModel.Factory)

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            val text =
                when(navBackStackEntry?.destination?.route) {
                    "history" -> "History"
                    "main" -> "Blood Pressure BPM Tracker"
                    "new_record" -> "New Record"
                    else -> ""
                }
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(navBackStackEntry?.destination?.route == "history" || navBackStackEntry?.destination?.route == "new_record") {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = text,
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
                    history = testHistoryList,
                    onViewAllHistoryButtonClick = {
                        navController.navigate(Screen.HistoryScreen.route)
                    },
                    onAddRecordButtonClick = {
                        navController.navigate(Screen.NewRecordScreen.route)
                    }
                )
            }

            composable(
                route = Screen.HistoryScreen.route
            ) {
                HistoryScreen(history = testHistoryList)
            }

            composable(
                route = Screen.NewRecordScreen.route
            ) {
                NewRecordScreen()
            }
        }
    }
}