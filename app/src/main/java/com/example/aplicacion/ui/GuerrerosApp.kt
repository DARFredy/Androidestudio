package com.example.aplicacion.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicacion.ui.theme.GuerrerosTheme

@Composable
fun GuerrerosApp() {
    GuerrerosTheme {
        val profileViewModel: ProfileViewModel = viewModel()
        var currentScreen by remember { mutableStateOf("Home") }

        Scaffold(
            bottomBar = {
                GuerrerosBottomNavigation(
                    currentScreen = currentScreen,
                    onScreenChange = { screen -> currentScreen = screen }
                )
            }
        ) { padding ->
            when (currentScreen) {
                "Home" -> HomeScreen(Modifier.padding(padding), profileViewModel)
                "Profile" -> ProfileScreen(Modifier.padding(padding), profileViewModel)
            }
        }
    }
}
