package com.android.navigationcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Composable function representing the second screen UI
@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    textToDisplay: String = ""
) {
    // Box composable to center the text vertically and horizontally
    Box(
        modifier = modifier.fillMaxSize(), // Fill the entire available size
        contentAlignment = Alignment.Center // Center align content
    ) {
        // Display the text with the provided input text
        Text(
            text = textToDisplay,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            color = Color.DarkGray
        )
    }
}
