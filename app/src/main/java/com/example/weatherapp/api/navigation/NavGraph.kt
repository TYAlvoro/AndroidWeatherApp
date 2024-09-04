package com.example.weatherapp.api.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.screens.AnimatedSplashScreen
import com.example.weatherapp.screens.HomeScreen
import com.example.weatherapp.screens.SearchScreen
import com.example.weatherapp.screens.SettingsScreen

@Composable
fun SetupNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen.Display(navController = navController)
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen.Display(navController = navController)
        }

        composable(route = Screen.Cities.route) {
            SearchScreen.Display(navController = navController)
        }
    }
}