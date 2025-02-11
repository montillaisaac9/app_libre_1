package com.example.electiva_libre.presentacion.ui.splash

import DesignSplashScreen


import com.example.electiva_libre.presentacion.ui.splash.components.AnimationSplashContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.electiva_libre.presentacion.navegation.Screen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenSplash(navController: NavController = rememberNavController(), viewModel: SplashViewModel = hiltViewModel()) {
    val scaleAnimation: Animatable<Float, AnimationVector1D> = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(viewModel) {
        viewModel.getUserDb()
    }

    if (viewModel.responseDataBase!= null){
        AnimationSplashContent(
            scaleAnimation = scaleAnimation,
            navController = navController,
            durationMillisAnimation = 1000,
            delayScreen = 100L
        ){
            navController.navigate(route = Screen.NewsScreen.route) {
                popUpTo(route = Screen.SplashScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
        DesignSplashScreen(
            scaleAnimation = scaleAnimation,
        )
    } else {
        AnimationSplashContent(
            scaleAnimation = scaleAnimation,
            navController = navController,
            durationMillisAnimation = 1000,
            delayScreen = 100L
        ){
            navController.navigate(route = Screen.LoginScreen.route) {
                popUpTo(route = Screen.SplashScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
        DesignSplashScreen(
            scaleAnimation = scaleAnimation,
        )
    }
}