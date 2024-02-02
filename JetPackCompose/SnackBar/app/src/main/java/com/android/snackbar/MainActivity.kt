package com.android.snackbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.android.snackbar.ui.theme.SnackBarTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplaySnackBar()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DisplaySnackBar() {
    // Create a SnackbarHostState to manage the state of the snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Create a CoroutineScope to launch coroutines for asynchronous operations
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    // Use the Scaffold composable to set up the basic structure of the screen
    Scaffold(
        // Pass the SnackbarHost to the Scaffold to handle snackbar display
        snackbarHost = { SnackbarHost(snackbarHostState) },
        // Set the content of the screen
        content = {
            // Button that triggers the display of the snackbar
            Button(
                onClick = {
                    coroutineScope.launch {
                        // Show the snackbar with a message, an action label, and an indefinite duration
                        snackbarHostState.showSnackbar(
                            message = "This is the message",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }
            ) {
                Text(text = "Display SnackBar")
            }
        }
    )
}
