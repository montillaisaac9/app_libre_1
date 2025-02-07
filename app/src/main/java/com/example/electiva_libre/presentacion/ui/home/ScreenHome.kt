package com.example.electiva_libre.presentacion.ui.home
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.electiva_libre.R

@Composable
fun ScreenHome(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row (modifier = Modifier.fillMaxWidth()){
                Icon(
                    painter = painterResource(id = R.drawable.ais),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.ais_logo),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                )
            }
        }
    }
}
