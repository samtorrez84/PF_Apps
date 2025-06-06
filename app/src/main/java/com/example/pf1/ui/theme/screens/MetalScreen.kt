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
fun MetalScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        // Imagen de encabezado
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_metal),
                contentDescription = "Metal",
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
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f))
                        ),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
            )
            Text(
                text = "Reciclaje de Metal",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Contenido informativo
        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader("Objetos comunes hechos de metal")
            TextList(
                listOf(
                    "Latas de aluminio",
                    "Latas de alimentos en conserva",
                    "Tapas de botellas",
                    "Cubiertos o utensilios",
                    "Chatarra de herramientas o piezas"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Símbolos de reciclaje más frecuentes")
            Text(
                text = "ALU • FE • Recyclable Metal",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Instrucciones para reciclar")
            TextList(
                listOf(
                    "Lava y seca las latas antes de desecharlas.",
                    "Aplasta las latas si es posible para ahorrar espacio.",
                    "Evita mezclar con otros materiales no reciclables.",
                    "Separa tapas o etiquetas plásticas."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Impacto ambiental")
            Text(
                text = "Reciclar una sola lata de aluminio ahorra la energía suficiente para hacer funcionar una televisión durante tres horas. El reciclaje de metales reduce la necesidad de extracción minera y disminuye la contaminación del suelo.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("metal") { inclusive = true }
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
