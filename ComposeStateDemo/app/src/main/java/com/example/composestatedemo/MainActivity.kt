package com.example.composestatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
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
                    val count   = viewModel.count
                    MyButton(currentCount = count){
                    viewModel.increaseCount()
                    }
                }
            }
        }
    }
}

