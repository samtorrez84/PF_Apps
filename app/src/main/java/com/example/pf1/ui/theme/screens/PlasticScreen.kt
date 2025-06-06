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
fun PlasticScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        // Imagen de encabezado con recorte decorativo y título superpuesto
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_plastic),
                contentDescription = "Plástico",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 32.dp,
                            bottomEnd = 32.dp
                        )
                    )
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
                text = "Reciclaje de Plástico",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        // Cuerpo con contenido informativo
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            SectionHeader("Objetos comunes hechos de este material")
            TextList(
                listOf(
                    "Botellas",
                    "Tapas",
                    "Bolsas",
                    "Envases de comida",
                    "Contenedores de detergente"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Símbolos de reciclaje más frecuentes")
            Text(
                text = "PET ① • HDPE ② • LDPE ④",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Instrucciones para reciclar")
            TextList(
                listOf(
                    "Enjuagar los envases antes de desechar.",
                    "No mezclar con comida ni otros materiales.",
                    "Separar tapas o etiquetas si es posible.",
                    "Compactar botellas para ahorrar espacio."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Impacto ambiental")
            Text(
                text = "Cada año, millones de toneladas de plástico no reciclado contaminan ríos y mares. El plástico puede tardar más de 400 años en degradarse y afecta gravemente a la fauna marina.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("plastic") { inclusive = true }
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

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        color = Color(0xFF1B5E20)
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
fun TextList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items.forEachIndexed { index, item ->
            Text(
                text = "${index + 1}. $item",
                fontSize = 15.sp,
                color = Color(0xFF444444),
                lineHeight = 20.sp
            )
        }
    }
}
