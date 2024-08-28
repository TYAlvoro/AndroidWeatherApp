package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.navigation.Screen

sealed class BaseScreen {
    @Composable
    open fun Content(paddingValues: PaddingValues) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color(0xfff7f7f7)).padding(paddingValues)
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

        BottomNavigation(
            backgroundColor = Color(0xff0851bf),
            modifier = Modifier.height(55.dp)
        ) {
            val sunImage: Painter = painterResource(id = R.drawable.splash_sun)

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

            BottomNavigationItem(
                selected = activeScreen == "homeScreen",
                onClick = {
                    navController.navigate(Screen.Home.route)
                },
                icon = {
                    Image(
                        modifier = Modifier
                            .size(35.dp),
                        painter = sunImage,
                        contentDescription = "Bottom Sun"
                    )
                }
            )

            BottomNavigationItem(
                selected = activeScreen == "searchScreen",
                onClick = {
                    navController.navigate(Screen.Cities.route)
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
        }
    }
}
