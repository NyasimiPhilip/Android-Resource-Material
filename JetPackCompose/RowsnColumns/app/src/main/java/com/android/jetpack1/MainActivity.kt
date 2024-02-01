package com.android.jetpack1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack1.ui.theme.JetPack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Using Row composable to arrange child composables horizontally
            Row(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize(),
                // Vertical alignment at the bottom and horizontal centering of child composables
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                // Display the greeting for "Android"
                Greeting("Android")
                // Display the greeting for "wabe"
                Greeting(name = "wabe")
                // Display the greeting for "bwa"
                Greeting(name = "bwa")
            }
        }
    }

    @Composable
    // Greeting composable to display a text message with specified styling
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            // Display the greeting message with the provided name
            text = "$name!",
            // Set font size, weight, color, and alignment
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Red,
            textAlign = TextAlign.Center,
            // Apply additional styling using modifier
            modifier = modifier
                .background(color = Color.Yellow)
                .border(2.dp, color = Color.Green)
                .padding(10.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    // GreetingPreview composable for previewing the Greeting composable
    fun GreetingPreview() {
        JetPack1Theme {
            // Display the greeting for "Android" in the theme preview
            Greeting("Android")
        }
    }
}
