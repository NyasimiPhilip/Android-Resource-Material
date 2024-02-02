package com.example.composestatedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme

// MainActivity class, extending ComponentActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity using Compose
        setContent {
            val viewModel = viewModel<MyViewModel>()
            // Apply the ComposeStateDemoTheme to the content
            ComposeStateDemoTheme {
                // Column composable that aligns its children in a vertical arrangement at the center
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Call the MyButton composable within the Column
                    // Mutable state representing the count
                    var count   = viewModel.count
                    MyButton(count)
                    { count = it + 1 }
                }
            }
        }
    }
}


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
