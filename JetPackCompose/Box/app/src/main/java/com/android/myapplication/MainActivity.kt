// Import necessary classes and libraries
package com.android.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// MainActivity class extending ComponentActivity
class MainActivity : ComponentActivity() {
    // onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content to BoxExample3
        setContent {
            BoxExample3()
        }
    }
}

// Composable function for BoxExample1
@Composable
fun BoxExample1() {
    // Outer Box with green background and specific size
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .size(180.dp, 300.dp)
    ) {
        // Inner Box with yellow background, smaller size, and aligned to the top end
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(125.dp, 100.dp)
                .align(Alignment.TopEnd)
        ) {
            // No content inside the inner box
        }

        // Text element with white background, specific size, and aligned to the bottom center
        Text(
            text = "Hi",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .background(color = Color.White)
                .size(90.dp, 50.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

// Composable function for BoxExample2
@Composable
fun BoxExample2() {
    // Outer Box with light gray background, filling the maximum size
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
    ) {
        // Text elements with yellow background, padding, and alignment for different positions
        Text(
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.TopStart),
            text = "TopStart"
        )
        Text(
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.TopCenter),
            text = "TopCenter"
        )

    }
}

// Composable function for BoxExample3
@Composable
fun BoxExample3() {
    // Box with Image, Text, and Button components
    Box(
        modifier = Modifier

    ) {
        // Image component with a resource ID for a beach resort
        Image(
            painter = painterResource(id = R.drawable.beach_resort),
            contentDescription = "beach resort"
        )
        // Text element with specific style, color, and alignment
        Text(
            text = "Beach Resort",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
        // Button element with an onClick listener, custom colors, and modifiers
        Button(
            onClick = {
                // Your onClick code here
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.DarkGray
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                //.border(5.dp, Color.DarkGray, RectangleShape)
        ) {
            Text("Add to Cart")
        }
    }
}

@Composable
@Preview
fun BoxExample3Preview() {
    BoxExample3()
}