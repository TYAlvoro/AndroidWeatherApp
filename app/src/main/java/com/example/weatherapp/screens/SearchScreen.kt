package com.example.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfff7f7f7))
                .padding(paddingValues)
        ) {
            items(10) { index ->
                ItemCard(index = index)
            }
        }
    }

    @Composable
    fun ItemCard(index : Int) {
        Card (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Item $index",
                modifier = Modifier.padding(16.dp),
                color = Color.Black // можно настроить цвета
            )
        }
    }
}