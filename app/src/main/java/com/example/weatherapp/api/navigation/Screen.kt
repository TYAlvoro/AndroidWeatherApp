package com.example.weatherapp.api.navigation


sealed class Screen(val route: String){
    object Splash : Screen(route = "splashScreen")
    object Home : Screen(route = "homeScreen")
    object Settings : Screen(route = "settingsScreen")
    object Cities : Screen(route = "searchScreen")
}