package com.android.navigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Composable function representing the navigation host of the application
@Composable
fun DemoAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    // Define the navigation graph using NavHost composable
    NavHost(navController = navController, startDestination = "home_screen") {

        // Composable for the home screen destination
        composable(route = "home_screen") {
            // Display the HomeScreen and navigate to the second screen when needed
            HomeScreen(
                onNavigateToSecondScreen = { inputText ->
                    navController.navigate("second_screen/$inputText")
                }
            )
        }

        // Composable for the second screen destination with a dynamic inputName argument
        composable(
            route = "second_screen/{inputName}", // Define route with a dynamic argument
            arguments = listOf(
                // Define the argument for inputName
                navArgument("inputName") {
                    type = NavType.StringType // Specify the type of the argument
                }
            )
        ) {
            // Display the SecondScreen with the input text obtained from the dynamic argument
            SecondScreen(
                textToDisplay = it.arguments?.getString("inputName").toString()
            )
        }
    }
}
