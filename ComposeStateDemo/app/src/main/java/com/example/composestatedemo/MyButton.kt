package com.example.composestatedemo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
@Composable
fun MyButton(currentCount: Int, updateCount:(Int)->Unit) {
    // Get the current context
    val context = LocalContext.current
    // Button composable with onClick listener


    Button(
        onClick = {
            // Increment the count when the button is clicked
            updateCount(currentCount)
        },
        // Button styling properties
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        // Text composable displaying the current count value
        Text(
            "Count is : $currentCount",
            // Styling using MaterialTheme's headlineMedium
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(5.dp)
        )
    }
}
