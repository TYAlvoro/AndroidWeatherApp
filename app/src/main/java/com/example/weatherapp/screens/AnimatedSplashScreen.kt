package com.example.weatherapp.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weatherapp.R
import com.example.weatherapp.api.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    var sunReachedCenter by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    LaunchedEffect(startAnimation) {
        if (startAnimation) {
            delay(2000)
            sunReachedCenter = true
        }
    }

    SplashScreen(startAnimation = startAnimation, sunReachedCenter = sunReachedCenter)
}

@Composable
fun SplashScreen(startAnimation: Boolean, sunReachedCenter: Boolean) {
    BoxWithConstraints(
        modifier = Modifier
            .background(Color(0xff0851bf))
            .fillMaxSize()
    ) {
        val screenWidth = maxWidth

        val sunAlpha by animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(1500), label = ""
        )
        val textAlpha by animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(1000), label = ""
        )
        val textScale by animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0.5f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), label = ""
        )
        val textOffset by animateDpAsState(
            targetValue = if (startAnimation) 0.dp else (-50).dp,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), label = ""
        )
        val cloud1Offset by animateDpAsState(
            targetValue = if (sunReachedCenter) -screenWidth else (-20).dp,
            animationSpec = tween(1000), label = ""
        )
        val cloud2Offset by animateDpAsState(
            targetValue = if (sunReachedCenter) screenWidth else 20.dp,
            animationSpec = tween(1000), label = ""
        )

        val sunImage: Painter = painterResource(id = R.drawable.splash_sun)
        val cloudImage1: Painter = painterResource(id = R.drawable.splash_cloud)
        val cloudImage2: Painter = painterResource(id = R.drawable.splash_cloud)

        Image(
            modifier = Modifier
                .size(100.dp)
                .align(alignment = Alignment.Center)
                .alpha(sunAlpha),
            painter = sunImage,
            contentDescription = "Splash Sun"
        )

        Image(
            modifier = Modifier
                .size(90.dp)
                .align(alignment = Alignment.Center)
                .offset(x = cloud1Offset, y = 25.dp)
                .alpha(sunAlpha),
            painter = cloudImage1,
            contentDescription = "Splash Cloud"
        )

        Image(
            modifier = Modifier
                .size(90.dp)
                .align(alignment = Alignment.Center)
                .offset(x = cloud2Offset, y = (-25).dp)
                .alpha(sunAlpha),
            painter = cloudImage2,
            contentDescription = "Splash Cloud"
        )

        val montserratFont = FontFamily(
            Font(R.font.montserrat, FontWeight.Normal)
        )

        Text(
            text = "Прогноз.y",
            fontSize = 40.sp,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier
                .alpha(textAlpha)
                .scale(textScale)
                .offset(y = textOffset)
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 200.dp)
        )
    }
}