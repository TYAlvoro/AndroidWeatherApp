package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.navigation.Screen

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            SettingsBottomNavigationBar(navController)
        }
    ) { contentPadding ->
        SettingsContent(paddingValues = contentPadding)
    }
}

@Composable
fun SettingsContent(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(5) { index ->
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
fun SettingsBottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color(0xff0851bf),
        modifier = Modifier.height(55.dp)
    ) {
        val sunImage: Painter = painterResource(id = R.drawable.splash_sun)

        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.navigate(Screen.Settings.route)
            },
            icon = { Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(35.dp),
                tint = Color.White
            ) }
        )

        BottomNavigationItem(
            selected = false,
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
            selected = false,
            onClick = {
                navController.navigate(Screen.Cities.route)
            },
            icon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(35.dp),
                tint = Color.White
            ) }
        )
    }
}