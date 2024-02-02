package com.example.composestatedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateDemoTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyButton()
                }
            }
        }
    }
}

val count = mutableIntStateOf(0)

@Preview(name = "MyButton")
@Composable
fun MyButton(){
    val context = LocalContext.current
    Button(onClick = {
        count.value = count.value+1
        Toast.makeText(context, "Count is : ${count.value}", Toast.LENGTH_SHORT).show()
    },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text("Count is : ${count.value}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(5.dp)
        )
    }
}

