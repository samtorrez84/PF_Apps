package com.example.pf1.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector


val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Composable
fun OptionsScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var anioNacimiento by remember { mutableStateOf("") }
    var mensajeConfirmacion by remember { mutableStateOf("") }
    var editando by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val prefs = context.dataStore.data.first()
        nombre = prefs[stringPreferencesKey("nombre")] ?: ""
        correo = prefs[stringPreferencesKey("correo")] ?: ""
        anioNacimiento = prefs[stringPreferencesKey("anioNacimiento")] ?: ""
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Configuración del perfil",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (editando) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = anioNacimiento,
                    onValueChange = { anioNacimiento = it },
                    label = { Text("Año de nacimiento") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        scope.launch {
                            context.dataStore.edit { prefs ->
                                prefs[stringPreferencesKey("nombre")] = nombre
                                prefs[stringPreferencesKey("correo")] = correo
                                prefs[stringPreferencesKey("anioNacimiento")] = anioNacimiento
                            }
                            mensajeConfirmacion = "Datos guardados correctamente"
                            editando = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = mainGreen)
                ) {
                    Text("Guardar", color = Color.White)
                }
            } else {
                Text("Nombre: $nombre", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text("Correo: $correo", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text("Año de nacimiento: $anioNacimiento", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { editando = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = mainGreen)
                ) {
                    Text("Editar", color = Color.White)
                }
            }

            if (mensajeConfirmacion.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = mensajeConfirmacion,
                    color = mainGreen,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Divider(
                color = Color(0xFFBDBDBD), // gris claro
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Text(
                text = "Soporte e información",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = mainGreen
            )



            Spacer(modifier = Modifier.height(12.dp))

            OptionItem(icon = Icons.Default.Info, title = "Acerca de", subtitle = "Versión 1.0.0")
            OptionItem(icon = Icons.Default.Email, title = "Contacto", subtitle = "storrezo2200@alumno.ipn.mx")
        }
    }
}

@Composable
fun OptionItem(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = mainGreen,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color.Black)
                Text(subtitle, fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}
