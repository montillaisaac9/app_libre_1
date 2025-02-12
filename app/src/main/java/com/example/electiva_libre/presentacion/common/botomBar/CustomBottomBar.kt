package com.example.electiva_libre.presentacion.common.botomBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Approval
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.electiva_libre.presentacion.navegation.Screen

@Composable
fun CustomBottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        ItemNavBar(
            icon = Icons.Outlined.Newspaper,
            title = "Noticias",
            route = Screen.NewsScreen.route
        ),
        ItemNavBar(
            icon = Icons.Outlined.Code,
            title = "Cursos",
            route = Screen.CourseScreen.route
        ),
        ItemNavBar(
            icon = Icons.Outlined.Approval,
            title = "Testimonios",
            route = Screen.TestimonialScreen.route
        ),
        ItemNavBar(
            icon = Icons.Outlined.PersonOutline,
            title = "Perfil",
            route = Screen.PerfilScreen.route
        )
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

data class ItemNavBar(
    val icon: ImageVector,
    val route: String,
    val title: String
)
