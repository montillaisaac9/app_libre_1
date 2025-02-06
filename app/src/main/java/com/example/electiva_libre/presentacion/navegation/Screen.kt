package com.example.electiva_libre.presentacion.navegation

sealed class Screen(val route:String) {
    data object LoginScreen: Screen("login_Screen")
    data object HomeScreen: Screen("home_Screen")
    data object SplashScreen: Screen("splash_Screen")

}