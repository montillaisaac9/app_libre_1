package com.example.electiva_libre.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.electiva_libre.presentacion.navegation.Navigation
import com.example.electiva_libre.presentacion.theme.Electiva_LibreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Electiva_LibreTheme {
                Navigation()
            }
        }
    }
}