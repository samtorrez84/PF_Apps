package com.example.pf1.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import com.example.pf1.ui.theme.screens.MapScreen
import com.example.pf1.utils.loadCentersFromCsv
import com.example.pf1.ui.theme.screens.CenterScreen
import com.example.pf1.ui.theme.screens.parseMarkers
import org.maplibre.android.geometry.LatLng

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val centers = loadCentersFromCsv(context) // Cargar datos al inicio

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
        composable(
            "map/{lat}/{lng}/{markers}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lng") { type = NavType.FloatType },
                navArgument("markers") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            // Recupera los argumentos pasados a la ruta
            val lat = backStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val lng = backStackEntry.arguments?.getFloat("lng")?.toDouble() ?: 0.0
            val markersString = backStackEntry.arguments?.getString("markers") ?: ""

            // Convierte la lista de marcadores desde el string
            val markers = parseMarkers(markersString)

            // Llama a `MapScreen` con los parámetros obtenidos
            MapScreen(
                navController = navController,
                centralMarker = LatLng(lat, lng),
                otherMarkers = markers
            )
        }

        composable("home") { HomeScreen(navController, defaultTab = "Basura", centers = centers) }

        composable("scan") { ScanScreen(navController, centers = centers)}
        composable("options") { OptionsScreen(navController) }

        composable("center/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val center = centers.find { it.id == id }
            if (center != null) {
                CenterScreen(navController, center)
            }
        }

        composable("puntos_acopio") {
            HomeScreen(navController = navController, defaultTab = "Puntos de acopio")
        }


    }
}


