package com.example.pf1.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pf1.R

@Composable
fun Center2Screen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        // Imagen de cabecera
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_center2), // coloca tu imagen
                contentDescription = "Recicladora Esmelter",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f))
                        ),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
            )
            Text(
                text = "Recicladora Esmelter",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader("Dirección")
            Text(
                text = "Quinta el Carmen, 90790 Papalotla, Tlaxcala, México",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Teléfono")
            Text(
                text = "222 729 73 39",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Materiales aceptados")
            TextList(
                listOf(
                    "Papel y cartón",
                    "Metales ferrosos y no ferrosos",
                    "Electrónicos y electrodomésticos",
                    "Baterías y acumuladores"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Recomendaciones")
            TextList(
                listOf(
                    "Separar el material por tipo antes de acudir.",
                    "Llamar previamente para confirmar horarios.",
                    "Evitar mezclar residuos peligrosos con reciclables."
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("puntos_acopio") {
                        popUpTo("centro2") { inclusive = true } // cambia "centro1" por la ruta real (ej. "centro_tlax1")
                    }
                },
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
}
