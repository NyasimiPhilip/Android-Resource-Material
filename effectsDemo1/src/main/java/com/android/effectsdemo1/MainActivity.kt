package com.android.effectsdemo1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.effectsdemo1.ui.theme.EffectsDemo1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectsDemo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    var round by remember { mutableIntStateOf(1) }
    var total by remember { mutableDoubleStateOf(0.0) }
    var input by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    /*
    LaunchedEffect(total) {
        if (total > 200) {
            snackbarHostState.showSnackbar("Total exceeded 200!")
        }
    }*/
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(50.dp), // Add additional padding if needed
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "Total is ${total.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.DarkGray
                )
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text("Enter value here") },
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    textStyle = TextStyle(
                        color = Color.LightGray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    label = { Text(text = "New count") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        val inputValue = input.toDoubleOrNull()
                        if (inputValue != null) {
                            total += inputValue
                            if (total > 200) {
                                total = 0.0
                                input = ""
                                round++
                                scope.launch {
                                    snackbarHostState.showSnackbar("Total exceeded 200!")
                                }
                            }
                        } else {
                            // Handle invalid input
                            Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(
                        text = "Count",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )

}
