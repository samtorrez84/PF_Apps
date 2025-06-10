package com.example.pf1.ui.theme.screens

import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import android.media.ExifInterface
import android.graphics.Matrix
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import com.example.pf1.clasificador.ImageClassifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ScanScreen(navController: NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var androidBitmap by remember { mutableStateOf<Bitmap?>(null) } // Guardamos Bitmap Android para liberar
    var classificationResults by remember { mutableStateOf<List<Pair<String, Float>>?>(null) }
    var isClassifying by remember { mutableStateOf(false) }
    val coroutineScope = remember { kotlinx.coroutines.CoroutineScope(Dispatchers.Main) }
    val classifier = remember { ImageClassifier(context) }

    val photoFile = remember {
        File.createTempFile(
            "IMG_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}",
            ".jpg",
            context.cacheDir
        )
    }
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", photoFile)

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            Log.d("ScanScreen", "Foto capturada con éxito: $uri")
            imageUri = uri
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            val exif = ExifInterface(photoFile.absolutePath)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            val rotatedBitmap = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270f)
                else -> bitmap
            }

            androidBitmap?.recycle() // Liberar bitmap anterior si hay
            androidBitmap = rotatedBitmap // Guardamos bitmap android
            imageBitmap = rotatedBitmap.asImageBitmap()
            classificationResults = null
            isClassifying = true
            Log.d("ScanScreen", "Comenzando clasificación de la imagen")

            coroutineScope.launch {
                val results = withContext(Dispatchers.IO) {
                    classifier.classify(rotatedBitmap)
                }
                classificationResults = results
                isClassifying = false
                Log.d("ScanScreen", "Clasificación completada: $results")
            }
        } else {
            Log.d("ScanScreen", "Captura de foto cancelada o fallida")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            androidBitmap?.recycle()
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Clasificación",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡Bienvenido al escáner de reciclaje!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Toma una foto de un objeto para clasificarlo y obtener información sobre cómo reciclarlo correctamente.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { cameraLauncher.launch(uri) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Tomar Foto", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (imageBitmap != null || isClassifying) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (imageBitmap != null) {
                            Image(
                                bitmap = imageBitmap!!,
                                contentDescription = "Imagen capturada",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        if (isClassifying) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator(color = Color.White)
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        "Analizando...",
                                        color = Color.White,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                classificationResults?.let { results ->
                    if (results.isNotEmpty()) {
                        val topResult = results[0]
                        Text(
                            "Resultado principal:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = topResult.first.replace("_", " ").replaceFirstChar { it.uppercase() },
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4CAF50)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Confianza: ${"%.1f".format(topResult.second)}%",
                                    fontSize = 16.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "¿Qué puedes escanear?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF4CAF50)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        listOf(
                            "Envases de plástico (botellas, tapas)",
                            "Papel y cartón (cajas, periódicos)",
                            "Desechos orgánicos (frutas, verduras)",
                            "Pilas y electrónicos",
                            "Envases metálicos",
                            "Botellas de vidrio"
                        ).forEach { item ->
                            Text(
                                text = item,
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}
