    package com.example.pf1.clasificador
    
    import android.content.Context
    import android.graphics.Bitmap
    import android.util.Log
    import org.tensorflow.lite.DataType
    import org.tensorflow.lite.support.common.ops.NormalizeOp
    import org.tensorflow.lite.support.image.ImageProcessor
    import org.tensorflow.lite.support.image.TensorImage
    import org.tensorflow.lite.support.image.ops.ResizeOp
    import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
    import org.tensorflow.lite.task.vision.classifier.ImageClassifier // Importación corregida
    
    class ImageClassifier(context: Context) {
        private var classifier: ImageClassifier? = null
        private val modelName = "recycling_classifier_model_with_metadata.tflite"
    
        init {
            try {
                // Usar ImageClassifier.ImageClassifierOptions en lugar de solo ImageClassifierOptions
                val options = ImageClassifier.ImageClassifierOptions.builder()
                    .setMaxResults(3)
                    .setScoreThreshold(0.3f)
                    .build()
    
                classifier = ImageClassifier.createFromFileAndOptions(
                    context,
                    modelName,
                    options
                )
            } catch (e: Exception) {
                Log.e("TFLite", "Error al inicializar el clasificador", e)
            }
        }
    
        fun classify(bitmap: Bitmap): List<Pair<String, Float>>? {
            if (classifier == null) return null
    
            // Preprocesamiento específico para MobileNetV2
            val imageProcessor = ImageProcessor.Builder()
                .add(ResizeWithCropOrPadOp(224, 224))
                .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
                .add(NormalizeOp(127.5f, 127.5f)) // (x / 127.5) - 1
                .build()
    
            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(bitmap)
            tensorImage = imageProcessor.process(tensorImage)
    
            // Clasificación
            val results = classifier?.classify(tensorImage)
    
            // Procesar resultados
            return results?.flatMap { classification ->
                classification.categories.map { category ->
                    Pair(category.label ?: "Unknown", category.score * 100)
                }
            }?.sortedByDescending { it.second }
        }
    }