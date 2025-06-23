package com.jkuat.cropdiagnosis

data class DiagnosisResult(
    val id: String = "",
    val cropType: String = "",
    val diseaseName: String = "",
    val confidence: Float = 0f,
    val description: String = "",
    val treatment: String = "",
    val prevention: String = "",
    val severity: DiseaseSeverity = DiseaseSeverity.LOW,
    val imageUrl: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val location: String = ""
)

enum class DiseaseSeverity(val displayName: String, val color: androidx.compose.ui.graphics.Color) {
    LOW("Low", androidx.compose.ui.graphics.Color(0xFF4CAF50)),
    MEDIUM("Medium", androidx.compose.ui.graphics.Color(0xFFFF9800)),
    HIGH("High", androidx.compose.ui.graphics.Color(0xFFF44336))
}

data class CropType(
    val id: String,
    val name: String,
    val scientificName: String,
    val iconRes: Int,
    val commonDiseases: List<String>
)