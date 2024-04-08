package com.example.pulse_app.ui.theme.composables.history_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse_app.classes.HistoryItem

@Composable
fun HistoryItemView(
    pulseData: HistoryItem
) {
    val containerColor = if(isSystemInDarkTheme()) Color.DarkGray else Color.White

    val date = pulseData.localDate
    val time = pulseData.localTime

    Surface(
        modifier = Modifier.padding(all=16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier.widthIn(min=50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pulseData.systolicPressure.toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = pulseData.diastolicPressure.toString(),
                    fontSize = 25.sp,
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
                    .width(7.dp)
                    .height(80.dp)
                    .background(
                        color = Color(0, 128, 104),
                        shape = RoundedCornerShape(10.dp)
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "$time, $date",
                    fontSize = 25.sp,
                )
                Text(
                    text = "Pulse: ${pulseData.pulse} BMP",
                    fontSize = 25.sp,
                )
            }
        }
    }
}