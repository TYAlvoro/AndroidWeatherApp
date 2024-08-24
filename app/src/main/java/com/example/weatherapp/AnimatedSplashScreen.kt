package com.example.weatherapp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    SplashScreen(startAnimation = startAnimation)
}

@Composable
fun SplashScreen(startAnimation: Boolean) {
    BoxWithConstraints(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.Cyan)
            .fillMaxSize()
    ) {
        val screenWidth = maxWidth
        val offsetAnimation by animateDpAsState(
            targetValue = if (startAnimation) 0.dp else -screenWidth,
            animationSpec = tween(3000)
        )

        Icon(
            modifier = Modifier
                .size(120.dp)
                .align(alignment = Alignment.Center)
                .offset(x = offsetAnimation),
            imageVector = Icons.Default.DateRange,
            contentDescription = "Logo",
            tint = Color.White,
        )
    }
}