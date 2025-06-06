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
fun CompostScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        // Imagen superior
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_compost),
                contentDescription = "Composta",
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
                text = "Composta Orgánica",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Contenido principal
        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader("Residuos comunes para composta")
            TextList(
                listOf(
                    "Cáscaras de frutas y verduras",
                    "Hojas secas y pasto",
                    "Bolsas de té y café",
                    "Cáscaras de huevo",
                    "Restos de comida natural sin procesar"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("¿Qué evitar en la composta?")
            TextList(
                listOf(
                    "Carne, pescado o lácteos",
                    "Aceites o grasas",
                    "Materiales tratados químicamente",
                    "Excremento de mascotas"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Instrucciones para compostar")
            TextList(
                listOf(
                    "Separar los residuos orgánicos de los inorgánicos.",
                    "Mezclar residuos húmedos (verdes) y secos (cafés) en proporción equilibrada.",
                    "Airear la mezcla una vez por semana.",
                    "Mantener la humedad parecida a una esponja exprimida.",
                    "En 2-3 meses se obtiene composta lista para abonar."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Beneficios ambientales")
            Text(
                text = "Compostar ayuda a reducir hasta un 50% los residuos domiciliarios, mejora la fertilidad del suelo, y disminuye la emisión de gases contaminantes como el metano en basureros.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("compost") { inclusive = true }
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
