package com.jkuat.cropdiagnosis.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jkuat.cropdiagnosis.DiagnosisResult
import com.jkuat.cropdiagnosis.DiseaseSeverity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    resultId: String,
    onNavigateBack: () -> Unit
) {
    val result = DiagnosisResult(
        id = resultId,
        cropType = "Tomato",
        diseaseName = "Late Blight",
        confidence = 0.92f,
        description = "Late blight is a serious disease affecting tomatoes caused by Phytophthora infestans.",
        treatment = "Apply copper-based fungicides immediately. Remove affected leaves and improve air circulation.",
        prevention = "Ensure proper spacing, avoid overhead watering, and use disease-resistant varieties.",
        severity = DiseaseSeverity.HIGH
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diagnosis Result") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")

                    }
                },
                actions = {
                    IconButton(onClick = { /* Save */ }) {
                        Icon(Icons.Default.BookmarkBorder, contentDescription = "Save")
                    }
                    IconButton(onClick = { /* Share */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DiagnosisCard(result)
            InfoSection("Description", Icons.Default.Info, result.description)
            InfoSection("Treatment", Icons.Default.LocalHospital, result.treatment, MaterialTheme.colorScheme.secondaryContainer)
            InfoSection("Prevention", Icons.Default.Shield, result.prevention, MaterialTheme.colorScheme.tertiaryContainer)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(onClick = { }) {
                    Icon(Icons.Default.Support, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Expert Help")
                }
                Button(onClick = { }) {
                    Icon(Icons.Default.CameraAlt, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Scan Again")
                }
            }
        }
    }
}

@Composable
fun DiagnosisCard(result: DiagnosisResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(result.diseaseName, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
                    Text("in ${result.cropType}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("${(result.confidence * 100).toInt()}%", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
                    Text("Confidence")
                }
            }

            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(50))
                        .background(result.severity.color)
                )
                Spacer(Modifier.width(8.dp))
                Text("${result.severity.displayName} Severity", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
fun InfoSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: String,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            }
            Spacer(Modifier.height(8.dp))
            Text(content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
