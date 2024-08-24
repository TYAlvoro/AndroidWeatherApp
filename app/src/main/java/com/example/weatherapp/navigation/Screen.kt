package com.example.weatherapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splashScreen")
    object Home : Screen(route = "homeScreen")
}