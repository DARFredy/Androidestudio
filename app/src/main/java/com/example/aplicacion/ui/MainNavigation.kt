package com.example.aplicacion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String, val label: String? = null, val icon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    object Splash : Screen("Splash")
    object Login : Screen("Login")
    object Register : Screen("Register")
    object Home : Screen("Home", "Inicio", Icons.Default.Home)
    object Profile : Screen("Profile", "Perfil", Icons.Default.Person)
    object WarriorDetail : Screen("warrior/{warriorId}")
}

val items = listOf(Screen.Home, Screen.Profile)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = items.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon!!, contentDescription = null) },
                            label = { Text(screen.label!!) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Splash.route) {
            composable(Screen.Splash.route) { SplashScreen(navController) }
            composable(Screen.Login.route) { LoginScreen(navController) }
            composable(Screen.Register.route) { RegisterScreen(navController) }
            composable(Screen.Home.route) { HomeScreen(modifier = Modifier.padding(innerPadding), profileViewModel = profileViewModel, onWarriorClicked = { warriorId -> navController.navigate("warrior/$warriorId") }) }
            composable(Screen.Profile.route) { ProfileScreen(modifier = Modifier.padding(innerPadding), profileViewModel = profileViewModel) }
            composable(Screen.WarriorDetail.route) { backStackEntry ->
                val warriorId = backStackEntry.arguments?.getString("warriorId")?.toIntOrNull()
                val warrior = getWarriors().find { it.id == warriorId }
                if (warrior != null) {
                    WarriorDetailScreen(warrior = warrior, onUp = { navController.popBackStack() })
                }
            }
        }
    }
}

// Screen for Warrior Details
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarriorDetailScreen(warrior: Warrior, onUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(warrior.name) },
                navigationIcon = { 
                    IconButton(onClick = onUp) { 
                        Icon(Icons.Default.ArrowBack, "Back") 
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = warrior.imageRes),
                contentDescription = warrior.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(warrior.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(warrior.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
