package com.example.pf1.ui.theme.screens

import android.net.Uri
import android.provider.MediaStore
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




@Composable
fun ScanScreen(navController: NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

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

            imageBitmap = rotatedBitmap.asImageBitmap()

        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(20.dp),
        ) {
            Text(
                text = "Clasificación",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )


            Spacer(modifier = Modifier.height(20.dp))

            // Remplaza el bloque largo de texto por esto:
            Text(
                text = "¡Bienvenido al escáner de reciclaje!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Esta herramienta te permitirá tomar una fotografía de un objeto o residuo que quieras reciclar. A partir de la imagen, la app te mostrará:",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "✅ Qué tipo de residuo es",
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    "✅ Si es reciclable o no",
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    "✅ Cómo debes separarlo",
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    "✅ Dónde puedes llevarlo",
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Text("¿Qué clases de residuos podrás escanear?", fontWeight = FontWeight.Medium, color = Color.Black)

            Text("- Envases de plástico (botellas, tapas, envolturas)", color = Color.DarkGray)
            Text("- Papel y cartón (revistas, cajas, servilletas)", color = Color.DarkGray)
            Text("- Desechos orgánicos (frutas, restos de comida)", color = Color.DarkGray)
            Text("- Otros (pilas, electrónicos, metal, vidrio)", color = Color.DarkGray)

            Spacer(modifier = Modifier.height(16.dp))






            Button(
                onClick = { cameraLauncher.launch(uri) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = mainGreen),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Abrir Cámara", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Imagen capturada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
    }
}

fun rotateBitmap(bitmap: android.graphics.Bitmap, degrees: Float): android.graphics.Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return android.graphics.Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

