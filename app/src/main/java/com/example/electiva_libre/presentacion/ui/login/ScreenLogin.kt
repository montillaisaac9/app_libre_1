package com.example.electiva_libre.presentacion.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.electiva_libre.R
import com.example.electiva_libre.data.network.models.responses.toEntity
import com.example.electiva_libre.presentacion.common.CustomProgressBar
import com.example.electiva_libre.presentacion.common.buttons.CustomButton
import com.example.electiva_libre.presentacion.common.inputs.CustomInput
import com.example.electiva_libre.presentacion.common.snackbar.CustomSnackbar
import com.example.electiva_libre.presentacion.navegation.Screen
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScreenLogin(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val stateLogin = viewModel.state
    val focusManager = LocalFocusManager.current
    val response = viewModel.logginResponse
    val context = LocalContext.current
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState
            ) {
                CustomSnackbar(
                    modifier = Modifier,
                    title = it.visuals.message,
                    subTitle = "",
                    animation = R.raw.error
                )
            }
        }
    ){ padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ais),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(R.string.ais_logo),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .padding(50.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Inicio de Sesion",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp).fillMaxWidth()
                )
                CustomInput(
                    state = stateLogin.username,
                    label = stringResource(R.string.username),
                    errorText = viewModel.state.errMsgUsername,
                    trailingIcon = if (stateLogin.username.isNotEmpty()) Icons.Outlined.Clear else null,
                    trailingIconDescription = if (stateLogin.username.isNotEmpty()) stringResource(R.string.clear_field) else null,
                    onTrailingIconClick = { viewModel.setTextUsername("")},
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                ){
                    viewModel.setTextUsername(it)
                }

                Spacer(modifier = Modifier.height(10.dp))
                CustomInput(
                    state = stateLogin.password,
                    label = stringResource(R.string.contrase_a),
                    errorText = stateLogin.errMsgPassword,
                    trailingIcon = if (stateLogin.password.isNotEmpty()) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    trailingIconDescription = if (stateLogin.password.isNotEmpty()) stringResource(R.string.show_password) else stringResource(R.string.hide_password),
                    onTrailingIconClick = {},
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onImeAction = { focusManager.clearFocus() }
                ){
                    viewModel.setTextPassword(it)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No tienes Cuenta Registrate",
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.RegisterScreen.route){
                                launchSingleTop = true
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                CustomButton(
                    text = "login",
                    onclick = {viewModel.validate()}
                )
            }
        }
    }
    LaunchedEffect(key1 = response) {
        when (response) {
            is ApiResult.Success -> {
                val user = response.data?.user?.toEntity()
                if (user != null) {
                    viewModel.inserUser(user = user)
                    navController.navigate(Screen.SplashScreen.route) {
                        popUpTo(route = Screen.LoginScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
            is ApiResult.Error -> {
                // Reiniciar el estado para evitar múltiples efectos
                viewModel.logginResponse = null
                val mensaje = if (response.error == "No active account found with the given credentials")
                    context.getString(R.string.not_acount)
                else
                    response.error.orEmpty()

                // Mostrar el Snackbar
                snackBarState.showSnackbar(message = mensaje)
            }
            is ApiResult.Loading -> {
                // Puedes mostrar un indicador de carga o una pantalla de progreso si lo deseas
            }
            null -> { /* No hacer nada */ }
        }
    }

    }