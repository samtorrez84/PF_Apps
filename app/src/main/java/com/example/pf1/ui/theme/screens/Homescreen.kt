package com.example.pf1.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pf1.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState

val backgroundWhite = Color(0xFFFFFFFF)
val mainGreen = Color(0xFF6B8E23)
val unselectedGray = Color(0xFF9E9E9E)

@Composable
fun HomeScreen(navController: NavController, defaultTab: String = "Basura") {
    var selectedTab by rememberSaveable { mutableStateOf(defaultTab) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFFFFF))
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "ReciclaApp",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Encuentra puntos de acopio de reciclaje",
                fontSize = 14.sp,
                color = Color(0xFF4E342E),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SegmentedButton("Basura", selectedTab == "Basura", mainGreen) {
                    selectedTab = "Basura"
                }
                Spacer(modifier = Modifier.width(8.dp))
                SegmentedButton("Puntos de acopio", selectedTab == "Acopio", mainGreen) {
                    selectedTab = "Acopio"
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                val content = if (selectedTab == "Basura") {
                    listOf(
                        Triple("Papel", "paper", R.drawable.img_paper),
                        Triple("Metal", "metal", R.drawable.img_metal),
                        Triple("Cartón", "cardboard", R.drawable.img_cardboard),
                        Triple("Plástico", "plastic", R.drawable.img_plastic),
                        Triple("Vidrio", "glass", R.drawable.img_glass),
                        Triple("Composta", "compost", R.drawable.img_compost),
                        Triple("Otros", "other", R.drawable.img_others)
                    )
                } else {
                    listOf(
                        Triple("Centro 1", "center1", R.drawable.ub),
                        Triple("Centro 2", "center2", R.drawable.ub),
                        Triple("Centro 3", "center3", R.drawable.ub)
                    )
                }

                items(content) { (title, route, imageRes) ->
                    RecyclableCard(
                        title = title,
                        description = "Información sobre $title...",
                        imageRes = imageRes,
                        onClick = { navController.navigate(route) }
                    )
                }
            }
        }
    }
}

@Composable
fun SegmentedButton(
    text: String,
    selected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) color else Color.Transparent,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = if (selected) 2.dp else 0.dp,
        border = BorderStroke(1.dp, color),
        modifier = Modifier
            .defaultMinSize(minWidth = 100.dp)
            .height(40.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (selected) Color.White else color,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun RecyclableCard(title: String, description: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(56.dp)
                    .padding(end = 12.dp)
            )
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF212121))
                Text(description, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Inicio",
                    tint = if (currentRoute == "home") mainGreen else unselectedGray
                )
            },
            label = {
                Text(
                    "Home",
                    color = if (currentRoute == "home") mainGreen else unselectedGray
                )
            }
        )

        NavigationBarItem(
            selected = currentRoute == "scan",
            onClick = {
                navController.navigate("scan") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.CameraAlt,
                    contentDescription = "Cámara",
                    tint = if (currentRoute == "scan") mainGreen else unselectedGray
                )
            },
            label = {
                Text(
                    "Cámara",
                    color = if (currentRoute == "scan") mainGreen else unselectedGray
                )
            }
        )

        NavigationBarItem(
            selected = currentRoute == "options",
            onClick = {
                navController.navigate("options") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Opciones",
                    tint = if (currentRoute == "options") mainGreen else unselectedGray
                )
            },
            label = {
                Text(
                    "Opciones",
                    color = if (currentRoute == "options") mainGreen else unselectedGray
                )
            }
        )
    }
}
