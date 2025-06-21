package com.example.pf1.ui.theme.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pf1.model.CenterData
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pf1.R
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Shape
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import coil.util.Logger
import okhttp3.Headers

@Composable
fun CenterScreen(navController: NavController, centerData: CenterData) {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val imageResId = context.resources.getIdentifier(
        centerData.imageUrl.trim(), // Nombre base del recurso sin extensión
        "drawable",          // Tipo de recurso
        context.packageName  // Nombre del paquete
    )

    Log.d("CenterScreen", "Image URL: ${centerData.imageUrl.trim()}")
    Log.d("CenterScreen", "Image Resource ID: $imageResId")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Log.d("CenterScreen", "Image URL: ${centerData.imageUrl}")

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Centro de Acopio",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        // Título del centro
        Text(
            text = centerData.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoRow(label = "Descripción:", info = centerData.description)
        InfoRow(label = "Dirección:", info = centerData.direccion)
        InfoRow(label = "Horario:", info = centerData.hours)
        InfoRow(label = "Teléfono:", info = centerData.phone)
        InfoRow(label = "Materiales:", info = centerData.materialsRecycled)

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para abrir la ubicación en Google Maps
        Button(
            onClick = {
                val uri = Uri.parse(centerData.urlGoogle)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                navController.context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B8E23)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Abrir en Google Maps", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B8E23)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Volver", color = Color.White, fontSize = 16.sp)
        }
    }
}