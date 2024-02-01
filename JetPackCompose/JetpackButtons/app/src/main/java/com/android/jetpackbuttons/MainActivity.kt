package com.android.jetpackbuttons

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Column layout to arrange buttons vertically
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // Call the ButtonDemo composable to display various button examples
                ButtonDemo()
            }
        }
    }
}

@Composable
fun ButtonDemo() {
    // Get the current context
    val context = LocalContext.current

    // Simple Button with a Text label
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    // Disabled Button with a Text label
    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
        },
        enabled = false
    ) {
        Text("Add To Cart")
    }

    // TextButton with a Text label
    TextButton(onClick = {
        Toast.makeText(context, "Clicked on Text Button", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    // OutlinedButton with a Text label
    OutlinedButton(onClick = {
        Toast.makeText(context, "Clicked on Outlined Button", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    // IconButton with a Refresh icon
    IconButton(onClick = {
        Toast.makeText(
            context,
            "Clicked on Icon Button",
            Toast.LENGTH_SHORT
        ).show()
    }) {
        Icon(
            Icons.Filled.Refresh,
            contentDescription = "Refresh Button",
            tint = Color.DarkGray,
            modifier = Modifier.size(80.dp)
        )
    }

    // Customized Button with additional styling options
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(5.dp)
        )
    }

    // Custom-shaped Button with additional styling options
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        shape = CutCornerShape(10.dp),
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(5.dp)
        )
    }

    // Circular-shaped Button with additional styling options
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        shape = CircleShape,
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(5.dp)
        )
    }
}

// Preview function for the ButtonDemo
@Composable
@Preview
fun ButtonDemoPreview() {
    // Use the ButtonDemo composable for preview
    ButtonDemo()
}
