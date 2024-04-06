package com.example.pulse_app.ui.theme.composables.history_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse_app.classes.HistoryItem

@Composable
fun HistoryItemView(
    pulseData: HistoryItem
) {
    val dateTime = pulseData.localDateTime

    Surface(
        modifier = Modifier.padding(all=16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 8.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = pulseData.diastolicPressure.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = pulseData.systolicPressure.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp
                    )
                    .width(10.dp)
                    .height(70.dp)
                    .background(
                        color = Color.Green,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = "${dateTime.hour}:${dateTime.minute}, ${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}",
                    fontSize = 30.sp,
                )
                Text(
                    text = "Pulse: ${pulseData.pulse}",
                    fontSize = 30.sp,
                )
            }
        }
    }
}