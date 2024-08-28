package com.example.weatherapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data object SearchScreen : BaseScreen() {
    @Composable
    fun Display(navController: NavHostController) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController, activeScreen = "searchScreen")
            }
        ) { contentPadding ->
            Content(paddingValues = contentPadding)
        }
    }

    @Composable
    override fun Content(paddingValues: PaddingValues) {
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
}