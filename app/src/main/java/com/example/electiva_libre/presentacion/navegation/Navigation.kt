package com.example.electiva_libre.presentacion.navegation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electiva_libre.presentacion.ui.Login.ScreenLogin
import com.example.electiva_libre.presentacion.ui.Splash.ScreenSplash

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) {
            ScreenSplash(navController)
        }
       composable(Screen.LoginScreen.route){
            ScreenLogin(navController)
        }/*        composable(Screen.HomeScreen.route) {
            ScreenHome(navController)
        }*/


    }

}