package com.example.electiva_libre.presentacion.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.electiva_libre.R
import com.example.electiva_libre.presentacion.common.buttons.CustomButton
import com.example.electiva_libre.presentacion.common.inputs.CustomInput
import com.example.electiva_libre.presentacion.navegation.Screen

@Composable
fun ScreenRegister(
    navController: NavHostController,
    viewModel: RegisterVIewModel = hiltViewModel()
) {
    val stateRegister = viewModel.state
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Logo o imagen superior (se puede reutilizar el recurso de logo)
        Icon(
            painter = painterResource(id = R.drawable.ais),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(R.string.ais_logo),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .padding(50.dp)
        )

        // Espaciado vertical
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            // Campo: Username
            CustomInput(
                state = stateRegister.username,
                label = stringResource(R.string.username),
                errorText = stateRegister.errMsgUsername,
                trailingIcon = if (stateRegister.username.isNotEmpty()) Icons.Outlined.Clear else null,
                trailingIconDescription = if (stateRegister.username.isNotEmpty()) stringResource(R.string.clear_field) else null,
                onTrailingIconClick = { viewModel.setTextUsername("") },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            ) { viewModel.setTextUsername(it) }

            Spacer(modifier = Modifier.height(10.dp))
            // Campo: Email
            CustomInput(
                state = stateRegister.email,
                label = stringResource(R.string.correo_electronico),
                errorText = stateRegister.errMsgEmail,
                trailingIcon = if (stateRegister.email.isNotEmpty()) Icons.Outlined.Clear else null,
                trailingIconDescription = if (stateRegister.email.isNotEmpty()) stringResource(R.string.clear_field) else null,
                onTrailingIconClick = { viewModel.setTextEmail("") },
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            ) { viewModel.setTextEmail(it) }

            Spacer(modifier = Modifier.height(10.dp))
            // Campo: Nombre
            CustomInput(
                state = stateRegister.name,
                label = stringResource(R.string.name),
                errorText = stateRegister.errMsgName,
                trailingIcon = if (stateRegister.name.isNotEmpty()) Icons.Outlined.Clear else null,
                trailingIconDescription = if (stateRegister.name.isNotEmpty()) stringResource(R.string.clear_field) else null,
                onTrailingIconClick = { viewModel.setTextName("") },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            ) { viewModel.setTextName(it) }

            Spacer(modifier = Modifier.height(10.dp))
            // Campo: Apellido
            CustomInput(
                state = stateRegister.lastname,
                label = stringResource(R.string.lastname),
                errorText = stateRegister.errMsgLastname,
                trailingIcon = if (stateRegister.lastname.isNotEmpty()) Icons.Outlined.Clear else null,
                trailingIconDescription = if (stateRegister.lastname.isNotEmpty()) stringResource(R.string.clear_field) else null,
                onTrailingIconClick = { viewModel.setTextLastname("") },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            ) { viewModel.setTextLastname(it) }

            Spacer(modifier = Modifier.height(10.dp))
            // Campo: Contraseña
            CustomInput(
                state = stateRegister.password,
                label = stringResource(R.string.password),
                errorText = stateRegister.errMsgPassword,
                trailingIcon = if (stateRegister.password.isNotEmpty()) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                trailingIconDescription = if (stateRegister.password.isNotEmpty()) stringResource(R.string.show_password) else stringResource(R.string.hide_password),
                onTrailingIconClick = { /* Aquí podrías implementar el toggle de visibilidad */ },
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            ) { viewModel.setTextPassword(it) }

            Spacer(modifier = Modifier.height(10.dp))
            // Campo: Confirmar Contraseña
            CustomInput(
                state = stateRegister.password2,
                label = stringResource(R.string.confirm_password),
                errorText = stateRegister.errMsgPassword2,
                trailingIcon = if (stateRegister.password2.isNotEmpty()) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                trailingIconDescription = if (stateRegister.password2.isNotEmpty()) stringResource(R.string.show_password) else stringResource(R.string.hide_password),
                onTrailingIconClick = { /* Aquí podrías implementar el toggle de visibilidad */ },
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onImeAction = { focusManager.clearFocus() }
            ) { viewModel.setTextPassword2(it) }

            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = "tienes Cuenta Inicar Session",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.HomeScreen.route){
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            // Botón para registrar
            CustomButton(
                text = stringResource(R.string.register),
                onclick = { viewModel.validate() }
            )
        }
    }
}