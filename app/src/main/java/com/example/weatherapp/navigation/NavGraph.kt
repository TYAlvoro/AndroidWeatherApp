package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.AnimatedSplashScreen
import com.example.weatherapp.HomeScreen
import com.example.weatherapp.SearchScreen
import com.example.weatherapp.SettingsScreen

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
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Settings.route) {
            SettingsScrфeen(navController = navController)
        }

        composable(route = Screen.Cities.route) {
            SearchScreen(navController = navController)
        }
    }
}