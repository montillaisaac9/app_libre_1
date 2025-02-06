package com.example.electiva_libre.presentacion.ui.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.electiva_libre.R

@Composable
fun ScreenLogin(navController: NavHostController) {

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
    }

}