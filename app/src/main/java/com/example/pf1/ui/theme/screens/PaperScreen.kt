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
fun PaperScreen(navController: NavController) {
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
                painter = painterResource(id = R.drawable.img_paper),
                contentDescription = "Papel",
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
                text = "Reciclaje de Papel",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Cuerpo informativo
        Column(modifier = Modifier.padding(20.dp)) {

            SectionHeader("Objetos comunes hechos de papel")
            TextList(
                listOf(
                    "Hojas y cuadernos usados",
                    "Cajas de cereal o zapatos",
                    "Sobres y revistas",
                    "Papel periódico",
                    "Cartulinas y empaques"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Símbolos de reciclaje más frecuentes")
            Text(
                text = "♼ PAP 20 • PAP 21 • PAP 22",
                fontSize = 15.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Instrucciones para reciclar")
            TextList(
                listOf(
                    "Evita mojar el papel, ya que húmedo no se puede reciclar.",
                    "Retira grapas o clips antes de desecharlo.",
                    "No mezcles papel con residuos orgánicos.",
                    "Cartones muy sucios (como cajas de pizza) no deben reciclarse."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader("Impacto ambiental")
            Text(
                text = "Reciclar una tonelada de papel puede salvar hasta 17 árboles y ahorrar más de 26,000 litros de agua. El papel reciclado genera un 70% menos contaminación del aire que el papel nuevo.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("paper") { inclusive = true }
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


