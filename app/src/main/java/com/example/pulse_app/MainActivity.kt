package com.example.pulse_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulse_app.ui.theme.PulseappTheme
import com.example.pulse_app.ui.theme.composables.PulseApp
import com.example.pulse_app.ui.theme.composables.new_record_screen.NewRecordScreen
import com.example.pulse_app.ui.theme.composables.new_record_screen.Scroller

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val surfaceColor = if(isSystemInDarkTheme()) Color(0xFF383A37) else Color(0xFFAAB3A8)

            PulseappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .background(color = surfaceColor),
                    //color = surfaceColor
                ) {
                    PulseApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PulseappTheme {
        Greeting("Android")
    }
}