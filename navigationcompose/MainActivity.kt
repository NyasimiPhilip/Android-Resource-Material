package com.android.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.android.navigationcompose.ui.theme.NavigationComposeTheme

// MainActivity class representing the entry point of the application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity
        setContent {
            // Apply the NavigationComposeTheme to the content
            NavigationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), // Fill the entire screen
                    color = MaterialTheme.colorScheme.background // Set the background color
                ) {
                    // Display the navigation host composable
                    DemoAppNavHost()
                }
            }
        }
    }
}
