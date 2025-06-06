package com.example.pf1.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pf1.R


@Composable
fun Center1Screen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Imagen representativa
        Image(
            painter = painterResource(id = R.drawable.img_center1), // Cambia por tu recurso
            contentDescription = "Centro de Acopio",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        // Título
        Text(
            text = "Centro de Acopio DIF Estatal Tlaxcala",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoRow(label = "Dirección:", info = "Av. Juárez 18, Col. Centro, Tlaxcala, Tlax.")
        InfoRow(label = "Horario:", info = "Lunes a viernes, 9:00 a 15:00 hrs.")
        InfoRow(label = "Teléfono:", info = "246 462 5000")
        InfoRow(label = "Materiales:", info = "PET, papel, cartón, aluminio, electrónicos.")

        Spacer(modifier = Modifier.height(20.dp))

        // Botón Volver
        Button(
            onClick = {
                navController.navigate("puntos_acopio") {
                    popUpTo("centro1") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B8E23)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Volver", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun InfoRow(label: String, info: String) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, color = Color(0xFF555555))
        Text(text = info, fontSize = 15.sp, color = Color(0xFF333333))
    }
}
