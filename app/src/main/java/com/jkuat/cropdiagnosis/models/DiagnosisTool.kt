package com.jkuat.cropdiagnosis.models

data class DiagnosisTool(
    val name: String,
    val description: String,
    val type: String,
    val platformLink: String,
    val cropsSupported: List<String>,
    val imageUrl: String
)
