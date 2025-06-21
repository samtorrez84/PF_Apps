package com.example.pf1.clasificador

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class ImageClassifier(private val context: Context) {

    private val modelName = "recycling_classifier_attention_with_metadata.tflite"
    private var imageClassifier: ImageClassifier? = null

    init {
        try {
            // Crear el clasificador usando el modelo con metadatos
            imageClassifier = ImageClassifier.createFromFile(context, modelName)
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error al cargar modelo con metadatos", e)
        }
    }

    fun classify(bitmap: Bitmap): List<Pair<String, Float>>? {
        if (imageClassifier == null) return null

        // Convertir Bitmap a TensorImage (requerido por la función classify)
        val tensorImage = TensorImage.fromBitmap(bitmap)

        // Ejecutar la inferencia en la imagen
        val results: List<Classifications> = imageClassifier!!.classify(tensorImage)
        Log.d("ImageClassifier", "Inferencia: $results")

        // Tomar el primer resultado (normalmente solo uno para clasificador simple)
        if (results.isEmpty()) return null

        val categories = results[0].categories

        // Mapear etiquetas y sus probabilidades en porcentaje, ordenadas de mayor a menor
        val output = categories.map { category ->
            category.label to category.score * 100f
        }.sortedByDescending { it.second }

        Log.d("ImageClassifier", "Resultados: $output")

        return output
    }
}
