package com.example.weatherapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data object SearchScreen : BaseScreen() {
    @Composable
    fun Display(navController: NavHostController) {
        var searchQuery by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                SearchBar(
                    searchQuery = searchQuery,
                    onQueryChanged = { searchQuery = it },
                    onSearchClicked = {}
                )
            },
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
                defaultElevation = 2.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.DarkGray,
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Item $index",
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }
    }

    @Composable
    fun SearchBar (
        searchQuery: String,
        onQueryChanged: (String) -> Unit,
        onSearchClicked : () -> Unit
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(BorderStroke(1.dp, Color.Black))
                .padding(20.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onQueryChanged,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text(text = "Найти город...") }
            )

            Button(
                onClick = onSearchClicked,
                modifier = Modifier.alignByBaseline()
            ) {
                Text("Search")
            }
        }
    }
}