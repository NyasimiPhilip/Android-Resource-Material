package com.android.uilayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.uilayer.ui.theme.UiLayerTheme
import kotlinx.coroutines.flow.collectLatest

// MainActivity class extending ComponentActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity
        setContent {
            // Apply UiLayerTheme to the content
            UiLayerTheme {
                // Create a surface container that fills the entire size of the screen
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display the MainScreen composable
                    MainScreen()
                }
            }
        }
    }
}

// Composable function representing the main screen UI
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
){
    // Create a SnackbarHostState to manage the display of Snackbars
    val snackbarHostState = remember { SnackbarHostState() }

    // Get the current screen state from the CounterViewModel
    val screenState = counterViewModel.screenState.value

    // LaunchedEffect to collect UI events from CounterViewModel
    LaunchedEffect(key1 = true ){
        counterViewModel.uiEventFlow.collectLatest {event->
            when(event){
                // When UIEvent.ShowMessage is received, show the Snackbar with the message
                is UIEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    // Scaffold composable providing the basic layout structure for the screen
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }, // Pass scaffoldState to Scaffold
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .padding(50.dp), // Add padding around the Column
                verticalArrangement = Arrangement.SpaceEvenly // Align children vertically
            ) {
                // Display the current count value
                Text(
                    modifier = modifier.fillMaxWidth(), // Fill the entire width
                    text = screenState.displayingResult,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.DarkGray
                )

                // Text field to enter a new count value
                OutlinedTextField(
                    value = screenState.inputValue,
                    onValueChange = {
                        // Invoke the appropriate event handler when the value changes
                        counterViewModel.onEvent(CounterEvent.ValueEntered(it))
                    },
                    modifier = modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(//Avoid Invalid input exceptions
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = TextStyle(
                        color = Color.LightGray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    label = { Text(text = "New Count") }
                )
                Spacer(modifier = Modifier.height(200.dp))

                // Button to count the value
                if(screenState.isCountButtonVisible) {
                    Button(
                        onClick = {
                            counterViewModel.onEvent(CounterEvent.CountButtonClicked)
                        },
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Count",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Button to reset the count value
                Button(
                    onClick = {
                        counterViewModel.onEvent(CounterEvent.ResetButtonClicked)
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Reset",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}
