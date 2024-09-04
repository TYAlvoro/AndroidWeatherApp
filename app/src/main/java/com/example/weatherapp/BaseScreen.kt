package com.example.weatherapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.navigation.Screen
import com.example.weatherapp.repository.GetWeather

sealed class BaseScreen {
    @Composable
    open fun Content(paddingValues: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfff7f7f7))
                .padding(paddingValues)
        ) {
            items(10) { index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController, activeScreen: String) {
        val cloudAlpha by animateFloatAsState(
            targetValue = if (activeScreen == "homeScreen") 0f else 1f, label = ""
        )

        BottomNavigation(
            backgroundColor = Color(0xff0851bf),
            modifier = Modifier.height(55.dp)
        ) {
            val sunImage: Painter = painterResource(id = R.drawable.splash_sun)
            val cloudImage: Painter = painterResource(id = R.drawable.splash_cloud)

            BottomNavigationItem(
                selected = activeScreen == "searchScreen",
                onClick = {
                    navController.navigate(Screen.Cities.route)
                    GetWeather()
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(35.dp),
                        tint = if (activeScreen == "searchScreen") Color(0xffa6c9ff) else Color.White
                    )
                }
            )

            BottomNavigationItem(
                selected = activeScreen == "homeScreen",
                onClick = {
                    navController.navigate(Screen.Home.route)
                },
                icon = {
                    Box(
                        modifier = Modifier.size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(35.dp),
                            painter = sunImage,
                            contentDescription = "Bottom Sun"
                        )

                        Image(
                            modifier = Modifier
                                .size(45.dp)
                                .alpha(cloudAlpha),
                            painter = cloudImage,
                            contentDescription = "Cloud Over Sun"
                        )
                    }
                }
            )

            BottomNavigationItem(
                selected = activeScreen == "settingsScreen",
                onClick = {
                    navController.navigate(Screen.Settings.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(35.dp),
                        tint = if (activeScreen == "settingsScreen") Color(0xffa6c9ff) else Color.White
                    )
                }
            )
        }
    }
}