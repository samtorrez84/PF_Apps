package com.example.pf1.ui.theme.screens

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import org.maplibre.android.MapLibre
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import android.util.Log
import androidx.compose.runtime.saveable.rememberSaveable
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.ArrowBack
import androidx.core.graphics.createBitmap
import org.maplibre.android.annotations.Icon
import org.maplibre.android.annotations.IconFactory
import com.example.pf1.R
import androidx.compose.material.icons.Icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun parseMarkers(markersString: String): List<Pair<LatLng, String>> {
    return if (markersString.isEmpty()) {
        emptyList()
    } else {
        markersString.split("|").mapNotNull { marker ->
            val parts = marker.split(",")
            if (parts.size == 3) {
                val lat = parts[0].toDoubleOrNull()
                val lng = parts[1].toDoubleOrNull()
                val title = parts[2]
                if (lat != null && lng != null) {
                    LatLng(lat, lng) to title
                } else {
                    null
                }
            } else {
                null
            }
        }
    }
}

@Composable
fun MapScreen(
    navController: NavController,
    centralMarker: LatLng? = null,
    otherMarkers: List<Pair<LatLng, String>>? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var savedState: Bundle? by rememberSaveable { mutableStateOf(null) }

    MapLibre.getInstance(context)

    val mapView = remember {
        MapView(context).apply {
            onCreate(savedState)
        }
    }

    DisposableEffect(Unit) {
        mapView.onStart()
        mapView.onResume()

        onDispose {
            savedState = Bundle().apply { mapView.onSaveInstanceState(this) }
            mapView.onPause()
            mapView.onStop()
            mapView.onDestroy()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { mv ->
            mv.getMapAsync { map ->
                map.setStyle("https://api.maptiler.com/maps/streets/style.json?key=PzOGGFE49Q9fYpNqpCCv") {
                    val defaultCentralMarker = LatLng(19.4326, -99.1332)
                    val initialPosition = centralMarker ?: defaultCentralMarker

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, 12.0))

                    centralMarker?.let {
                        map.addMarker(
                            MarkerOptions()
                                .position(it)
                                .title("Tu ubicación")
                                .icon(IconFactory.getInstance(context).fromResource(R.drawable.ic_location))
                        )
                    }

                    otherMarkers?.forEach { (location, title) ->
                        map.addMarker(
                            MarkerOptions()
                                .position(location)
                                .title(title)
                        )
                    }
                }
            }
        }

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.BottomEnd)  // abajo derecha
                .padding(16.dp)
                .size(48.dp)  // tamaño del botón
                .background(Color.Red, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = Color.White
            )
        }
    }
}
