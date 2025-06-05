package com.example.pf1.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ScanScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("¿Qué estás reciclando?", style = MaterialTheme.typography.headlineMedium)
        IconButton(onClick = { /* abrir cámara */ }) {
            Icon(Icons.Default.CameraAlt, contentDescription = null)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar")
        }
    }
}