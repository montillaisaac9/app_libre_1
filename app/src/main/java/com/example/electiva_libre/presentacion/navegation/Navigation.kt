package com.example.electiva_libre.presentacion.navegation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electiva_libre.presentacion.ui.courses.ScreenCourses
import com.example.electiva_libre.presentacion.ui.login.ScreenLogin
import com.example.electiva_libre.presentacion.ui.news.ScreenNews
import com.example.electiva_libre.presentacion.ui.perfil.ScreenPerfil
import com.example.electiva_libre.presentacion.ui.register.ScreenRegister
import com.example.electiva_libre.presentacion.ui.splash.ScreenSplash
import com.example.electiva_libre.presentacion.ui.testimonials.ScreenTestimonials

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) {
            ScreenSplash(navController)
        }
       composable(Screen.LoginScreen.route){
            ScreenLogin(navController)
        }
        composable(Screen.NewsScreen.route) {
            ScreenNews(navController)
        }
        composable(Screen.CourseScreen.route) {
            ScreenCourses(navController)
        }
        composable(Screen.PerfilScreen.route) {
            ScreenPerfil(navController)
        }
        composable(Screen.TestimonialScreen.route) {
            ScreenTestimonials(navController)
        }
        composable(Screen.RegisterScreen.route) {
            ScreenRegister(navController)
        }


    }

}