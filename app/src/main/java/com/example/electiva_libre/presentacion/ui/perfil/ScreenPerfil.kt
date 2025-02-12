package com.example.electiva_libre.presentacion.ui.perfil
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.electiva_libre.R
import com.example.electiva_libre.presentacion.common.CustomProgressBar
import com.example.electiva_libre.presentacion.common.botomBar.CustomBottomBar
import com.example.electiva_libre.presentacion.common.buttons.CustomButton
import com.example.electiva_libre.presentacion.navegation.Screen
import kotlinx.coroutines.launch

@Composable
fun ScreenPerfil(
    navController: NavHostController,
    viewModel: PerfilViewModel = hiltViewModel()
) {

    val user = viewModel.user
    val scope = rememberCoroutineScope()

    Scaffold (
        bottomBar = {
            CustomBottomBar(navController)
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ais),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.ais_logo),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                )
                Text("Perfil", color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
            }
            if (user.email.isEmpty()) {
                CustomProgressBar()
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(200.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .border(
                            color = MaterialTheme.colorScheme.onBackground,
                            width = 1.dp,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        contentDescription = "foto",
                        imageVector = Icons.Outlined.PersonOutline,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
                Text("Perfil", color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
                Text("Nombre de Usuario: ${user.username}")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                Text("Nombre: ${user.firstName}")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                Text("Apellido: ${user.lastName}")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                Text("E-mail: ${user.email}")
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    CustomButton(
                        text = "Cerrar Sesion",
                        onclick = {
                            viewModel.logout()
                            navController.navigate(Screen.SplashScreen.route) {
                                popUpTo(Screen.PerfilScreen.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}


