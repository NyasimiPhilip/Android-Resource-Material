package com.android.flowscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.android.flowscompose.ui.theme.FlowsComposeTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    // Use the viewModel() function to initialize MyViewModel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Call your @Composable function here
            MyApp(viewModel.myFlow)
        }
    }
}

@Composable
fun MyApp(myFlow: Flow<Int>) {
    val currentValue = myFlow.collectAsState(initial = 1).value

    FlowsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                ("Hello $currentValue")
            )
        }
    }
}
