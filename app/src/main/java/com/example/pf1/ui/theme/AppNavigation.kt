package com.example.pf1.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pf1.ui.theme.screens.HomeScreen
import com.example.pf1.ui.theme.screens.ScanScreen
import com.example.pf1.ui.theme.WelcomeScreen
import com.example.pf1.ui.theme.screens.PaperScreen
import com.example.pf1.ui.theme.screens.MetalScreen
import com.example.pf1.ui.theme.screens.CardboardScreen
import com.example.pf1.ui.theme.screens.PlasticScreen
import com.example.pf1.ui.theme.screens.GlassScreen
import com.example.pf1.ui.theme.screens.CompostScreen
import com.example.pf1.ui.theme.screens.OtherScreen
import com.example.pf1.ui.theme.screens.OptionsScreen
import com.example.pf1.ui.theme.screens.Center1Screen
import com.example.pf1.ui.theme.screens.Center2Screen
import com.example.pf1.ui.theme.screens.Center3Screen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("main") {
            MainScreen()
        }

        composable("scan") { ScanScreen(navController) }

        // Rutas a los materiales reciclables/
        composable("paper") { PaperScreen(navController) }
        composable("metal") { MetalScreen(navController) }
        composable("cardboard") { CardboardScreen(navController) }
        composable("plastic") { PlasticScreen(navController) }
        composable("glass") { GlassScreen(navController) }
        composable("compost") { CompostScreen(navController) }
        composable("other") { OtherScreen(navController) }

        composable("home") { HomeScreen(navController, defaultTab = "Basura") }

        composable("scan") { ScanScreen(navController) }
        composable("options") { OptionsScreen(navController) }

        composable("center1") { Center1Screen(navController) }
        composable("center2") { Center2Screen(navController) }
        composable("center3") { Center3Screen(navController) }

        composable("puntos_acopio") {
            HomeScreen(navController = navController, defaultTab = "Puntos de acopio")
        }


    }
}


