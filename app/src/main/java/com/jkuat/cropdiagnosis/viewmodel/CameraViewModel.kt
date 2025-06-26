package com.jkuat.cropdiagnosis.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkuat.cropdiagnosis.ClassificationResult
import com.jkuat.cropdiagnosis.DiagnosisResult
import com.jkuat.cropdiagnosis.MLClassifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import com.jkuat.cropdiagnosis.DiseaseSeverity



@HiltViewModel
class CameraViewModel @Inject constructor(
    private val classifier: MLClassifier
) : ViewModel() {

    private val _predictionResult = MutableStateFlow<String?>(null)
    val predictionResult = _predictionResult.asStateFlow()

    init {
        viewModelScope.launch {
            classifier.initialize()
        }
    }

    fun classifyImage(bitmap: Bitmap) {
        viewModelScope.launch {
            val result: ClassificationResult? = classifier.classify(bitmap)

            result?.let {
                val resultId = UUID.randomUUID().toString()
                val diagnosis = DiagnosisResult(
                    id = resultId,
                    cropType = "Unknown", // You can improve this by mapping labels to crop types
                    diseaseName = it.label,
                    confidence = it.confidence,
                    description = "Details coming soon...",
                    treatment = "Use recommended fungicide/pesticide.",
                    prevention = "Practice crop rotation and monitor regularly.",
                    severity = when {
                        it.confidence >= 0.8 -> DiseaseSeverity.HIGH
                        it.confidence >= 0.5 -> DiseaseSeverity.MEDIUM
                        else -> DiseaseSeverity.LOW
                    },
                    imageUrl = "", // Optional
                    timestamp = System.currentTimeMillis(),
                    location = "" // Optional GPS integration
                )

                // Simulate saving to database or remote service here (e.g. Firebase)
                _predictionResult.value = diagnosis.id
            }
        }
    }

    fun resetState() {
        _predictionResult.value = null
    }
}
