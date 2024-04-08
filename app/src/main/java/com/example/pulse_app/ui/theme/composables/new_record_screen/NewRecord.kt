package com.example.pulse_app.ui.theme.composables.new_record_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pulse_app.R
import com.example.pulse_app.classes.HistoryItem
import com.example.pulse_app.utils.convertLocalDateTimeToDate
import com.example.pulse_app.utils.convertMillisToDate
import com.example.pulse_app.view_models.HistoryViewModel
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecordScreen(
    viewModel: HistoryViewModel,
    onSaveButtonClick: (HistoryItem) -> Unit
) {

    val containerColor = if(isSystemInDarkTheme()) Color.DarkGray else Color.White

    val datePickerState = rememberDatePickerState()

    val timePickerState = rememberTimePickerState()

    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }

    var showTimePickerDialog by remember {
        mutableStateOf(false)
    }

    var localDateTime by remember {
       mutableStateOf( convertLocalDateTimeToDate(LocalDateTime.now()))
    }


    var time by remember {
        mutableStateOf(LocalDateTime.now().toString().drop(11).take(5))
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                    //.padding(all=8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Scroller(
                    viewModel = viewModel,
                    paramName = "Diastolic",
                    measurementUnit = "mmHg"
                )
                Scroller(
                    viewModel = viewModel,
                    paramName = "Systolic",
                    measurementUnit = "mmHg"
                )
                Scroller(
                    viewModel = viewModel,
                    paramName = "Pulse",
                    measurementUnit = "BMP"
                )
            }

            Text(
                modifier = Modifier.padding(top=8.dp, bottom = 8.dp, end=8.dp, start=16.dp),
                text = "Date & Time",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )


            Row {
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .clickable {
                            showDatePickerDialog = true
                        }
                        /*.border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )*/
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = containerColor),
                ) {
                    Box(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 30.dp
                        )
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_calendar_today_24),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = localDateTime
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable {
                            showTimePickerDialog = true
                        }
                        /*.border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )*/
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = containerColor),
                ) {
                    Box(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 30.dp
                        )
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_access_time_24),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = time
                            )
                        }
                    }
                }

            }

            Surface(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable {
                        onSaveButtonClick(
                            HistoryItem(
                                id = 0,
                                systolicPressure = viewModel.systolicPressure,
                                diastolicPressure = viewModel.diastolicPressure,
                                localDate = viewModel.date,
                                localTime = viewModel.time,
                                pulse = viewModel.pulse
                            )
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(0, 128, 104),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(all = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Save",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


        }

        if(showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePickerDialog = false
                },
                confirmButton = {
                    Button(onClick = {
                        showDatePickerDialog = false
                        localDateTime =
                            datePickerState.selectedDateMillis?.let {
                                convertMillisToDate(
                                    it
                                )
                            } ?: ""
                        viewModel.setDateValue(localDateTime)
                    }) {
                        Text(text = "OK")
                    } }) {
                DatePicker(state = datePickerState)
            }
        }

        if(showTimePickerDialog) {
            TimePickerDialog(
                onDismissRequest = { showTimePickerDialog = false },
                confirmButton = { Button(onClick = {
                    showTimePickerDialog = false
                    val hour = StringBuilder(timePickerState.hour.toString())
                    val minute = StringBuilder(timePickerState.minute.toString())
                    if(hour.toString().length == 1){
                        hour.insert(0, "0")
                    }
                    if(minute.toString().length == 1){
                        minute.insert(0, "0")
                    }
                    time = "$hour:$minute"
                    viewModel.setTimeValue(time)
                }) {
                    Text(text = "OK")
                } },
                dismissButton = { /*TODO*/ }
            ) {
                TimePicker(state = timePickerState)
            }
        }


    }
}


@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}


