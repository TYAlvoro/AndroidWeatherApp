package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
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
            modifier = Modifier.padding(paddingValues)
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
    fun BottomNavigationBar(navController: NavHostController) {
        var selectedItem by remember { mutableStateOf(0) }

        BottomNavigation(
            backgroundColor = Color(0xff0851bf),
            modifier = Modifier.height(55.dp)
        ) {
            val sunImage: Painter = painterResource(id = R.drawable.splash_sun)

            BottomNavigationItem(
                selected = selectedItem == 0,
                onClick = {
                    selectedItem = 0
                    navController.navigate(Screen.Settings.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(35.dp),
                        tint = if (selectedItem == 0) Color.Yellow else Color.White
                    )
                }
            )

            BottomNavigationItem(
                selected = selectedItem == 1,
                onClick = {
                    selectedItem = 1
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
                selected = selectedItem == 2,
                onClick = {
                    selectedItem = 2
                    navController.navigate(Screen.Cities.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(35.dp),
                        tint = if (selectedItem == 2) Color.Yellow else Color.White
                    )
                }
            )
        }
    }
}
