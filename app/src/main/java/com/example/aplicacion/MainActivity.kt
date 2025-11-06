package com.example.aplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.aplicacion.ui.MainNavigation
import com.example.aplicacion.ui.theme.GuerrerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuerrerosTheme {
                MainNavigation()
            }
        }
    }
}
