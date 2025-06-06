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
fun CardboardScreen(navController: NavController) {
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
                painter = painterResource(id = R.drawable.img_cardboard),
                contentDescription = "Cartón",
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
                text = "Reciclaje de Cartón",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Contenido
        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader("Objetos comunes hechos de cartón")
            TextList(
                listOf(
                    "Cajas de zapatos",
                    "Cajas de cereal o comida",
                    "Tubos de papel higiénico",
                    "Empaques de cartón",
                    "Cajas de embalaje"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Símbolos de reciclaje más frecuentes")
            Text(
                text = "♼ PAP 20 (Corrugado) • PAP 21 (No corrugado)",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Instrucciones para reciclar")
            TextList(
                listOf(
                    "Dobla o aplasta las cajas para ahorrar espacio.",
                    "Evita que se mojen o ensucien con alimentos.",
                    "Retira cintas adhesivas o etiquetas si es posible.",
                    "No mezcles con cartón encerado o plastificado."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Impacto ambiental")
            Text(
                text = "El cartón reciclado ayuda a reducir la tala de árboles y disminuye el uso de energía en procesos industriales. Puede reciclarse hasta 7 veces antes de degradarse completamente.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("cardboard") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B8E23)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Volver a Basura", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
