package com.jkuat.cropdiagnosis

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MLClassifier @Inject constructor(
    private val context: Context
) {
    private var interpreter: Interpreter? = null
    private var labels: List<String> = emptyList()
    private val imageSize = 224

    private val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(imageSize, imageSize, ResizeOp.ResizeMethod.BILINEAR))
        .build()

    suspend fun initialize() {
        try {
            val modelBuffer = FileUtil.loadMappedFile(context, "crop_disease_model.tflite")
            interpreter = Interpreter(modelBuffer)
            labels = FileUtil.loadLabels(context, "labels.txt")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun classify(bitmap: Bitmap): ClassificationResult? {
        return try {
            val tensorImage = TensorImage.fromBitmap(bitmap)
            val processedImage = imageProcessor.process(tensorImage)

            val outputBuffer = TensorBuffer.createFixedSize(
                intArrayOf(1, labels.size),
                org.tensorflow.lite.DataType.FLOAT32
            )

            interpreter?.run(processedImage.buffer, outputBuffer.buffer)

            val tensorLabel = TensorLabel(labels, outputBuffer)
            val result = tensorLabel.mapWithFloatValue

            val maxEntry = result.maxByOrNull { it.value }

            ClassificationResult(
                label = maxEntry?.key ?: "",
                confidence = maxEntry?.value ?: 0f,
                allResults = result
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun close() {
        interpreter?.close()
    }
}

data class ClassificationResult(
    val label: String,
    val confidence: Float,
    val allResults: Map<String, Float>
)