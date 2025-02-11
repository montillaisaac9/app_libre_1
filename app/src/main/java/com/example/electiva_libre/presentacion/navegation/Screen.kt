package com.example.electiva_libre.presentacion.navegation

sealed class Screen(val route:String) {
    data object LoginScreen: Screen("login_Screen")
    data object SplashScreen: Screen("splash_Screen")
    data object RegisterScreen: Screen("register_Screen")
    data object CourseScreen: Screen("course_Screen")
    data object NewsScreen: Screen("news_Screen")
    data object TestimonialScreen: Screen("testimonials_Screen")
    data object PerfilScreen: Screen("perfil_Screen")

}