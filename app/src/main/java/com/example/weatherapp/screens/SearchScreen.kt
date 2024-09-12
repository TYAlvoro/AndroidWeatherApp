package com.example.weatherapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weatherapp.api.repository.getCity
import com.example.weatherapp.api.repository.getWeather

data object SearchScreen : BaseScreen() {
    @Composable
    fun Display(navController: NavHostController) {
        var searchQuery by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                SearchBar(
                    searchQuery = searchQuery,
                    onQueryChanged = { searchQuery = it },
                    onSearchClicked = { searchCity() }
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
            items(2) { index ->
                ItemCard(index = index)
            }
        }
    }

    @Composable
    fun ItemCard(index : Int) {
        Card (
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .height(100.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color(0xffb0b0b0)),
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
    fun SearchBar(
        searchQuery: String,
        onQueryChanged: (String) -> Unit,
        onSearchClicked: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .border(border = BorderStroke(1.dp, Color(0xffbababa)), shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 8.dp, vertical = 1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onQueryChanged,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = {
                    Text(
                        text = "Найти город...",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Gray
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    cursorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            IconButton(
                onClick = onSearchClicked,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 4.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
        }
    }

    fun searchCity() {
        getWeather()
        getCity()
    }
}
