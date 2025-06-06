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
fun OtherScreen(navController: NavController) {
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
                painter = painterResource(id = R.drawable.img_others),
                contentDescription = "Otros residuos",
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
                text = "Otros Residuos",
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
            SectionHeader("Ejemplos de residuos diversos")
            TextList(
                listOf(
                    "Pilas o baterías",
                    "Electrodomésticos rotos",
                    "Medicamentos caducados",
                    "Textiles o ropa dañada",
                    "Juguetes plásticos no reciclables"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Manejo adecuado")
            TextList(
                listOf(
                    "No tirar a la basura común.",
                    "Llevar a centros de acopio especializados o campañas municipales.",
                    "Consultar con tu municipio o colegio sobre eventos de recolección segura."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Peligros ambientales")
            Text(
                text = "Residuos como pilas y electrónicos contienen sustancias tóxicas que contaminan el agua y el suelo. Desecharlos incorrectamente daña gravemente la salud humana y el ecosistema.",
                fontSize = 15.sp,
                color = Color(0xFF444444),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("other") { inclusive = true }
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
