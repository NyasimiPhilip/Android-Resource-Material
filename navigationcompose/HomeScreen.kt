package com.android.navigationcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function representing the home screen UI
@Composable
fun HomeScreen(onNavigateToSecondScreen: (String) -> Unit, modifier: Modifier = Modifier) {
    // Define a mutable state for the text input
    var text by remember { mutableStateOf("") }

    // Column composable for arranging UI elements vertically
    Column(
        modifier = modifier
            .fillMaxSize() // Occupy the maximum available size
            .padding(60.dp), // Add padding around the column
        verticalArrangement = Arrangement.SpaceEvenly, // Arrange children with equal spacing vertically
        horizontalAlignment = Alignment.CenterHorizontally, // Center align children horizontally
    ) {
        // OutlinedTextField for user input
        OutlinedTextField(
            value = text,
            onValueChange = { text = it }, // Update the text value when user input changes
            modifier = modifier.fillMaxWidth(), // Fill the entire width
            textStyle = TextStyle(
                color = Color.DarkGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )

        // Button for submitting the input text
        Button(
            onClick = {
                if (text != "") { // Check if input text is not empty
                    onNavigateToSecondScreen(text) // Navigate to the second screen passing the input text
                }
            },
            modifier = modifier.fillMaxWidth() // Fill the entire width
        ) {
            Text(
                text = "Submit",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
