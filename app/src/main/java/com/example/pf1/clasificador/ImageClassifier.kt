package com.example.pf1.clasificador

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class ImageClassifier(private val context: Context) {

    private val modelName = "recycling_classifier_model_with_metadata.tflite"
    private var interpreter: Interpreter? = null
    private var labels: List<String> = emptyList()

    init {
        labels = loadLabels("labels.txt")
        try {
            val modelBuffer = loadModelFile(context, modelName)
            interpreter = Interpreter(modelBuffer)
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error al cargar modelo TFLite", e)
        }
    }

    private fun loadLabels(filename: String): List<String> {
        val labelList = mutableListOf<String>()
        try {
            context.assets.open(filename).bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    if (line.isNotBlank()) labelList.add(line.trim())
                }
            }
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error al cargar labels desde assets", e)
        }
        return labelList
    }

    @Throws(IOException::class)
    private fun loadModelFile(context: Context, modelName: String): ByteBuffer {
        val assetFileDescriptor = context.assets.openFd(modelName)
        val inputStream = assetFileDescriptor.createInputStream()
        val fileChannel = inputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength

        val byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        byteBuffer.order(ByteOrder.nativeOrder())
        return byteBuffer
    }

    fun classify(bitmap: Bitmap): List<Pair<String, Float>>? {
        if (interpreter == null) return null

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeWithCropOrPadOp(224, 224))
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(127.5f, 127.5f))
            .build()

        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        tensorImage = imageProcessor.process(tensorImage)

        val inputBuffer = tensorImage.buffer
        val outputBuffer = Array(1) { FloatArray(labels.size) }

        interpreter?.run(inputBuffer, outputBuffer)

        val probabilities = outputBuffer[0]

        val results = labels.mapIndexed { index, label ->
            label to probabilities[index] * 100f
        }.sortedByDescending { it.second }

        Log.d("ImageClassifier", "Resultados: $results")

        return results
    }
}
