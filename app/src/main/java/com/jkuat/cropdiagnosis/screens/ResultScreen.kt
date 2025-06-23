package com.jkuat.cropdiagnosis.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
    // Mock result - replace with actual data loading
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Diagnosis Result") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { /* Save result */ }) {
                    Icon(Icons.Default.BookmarkBorder, contentDescription = "Save")
                }
                IconButton(onClick = { /* Share result */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Confidence card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (result.confidence >= 0.8f)
                        MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = result.diseaseName,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "in ${result.cropType}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "${(result.confidence * 100).toInt()}%",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Confidence",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Severity indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(result.severity.color)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${result.severity.displayName} Severity",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            // Description section
            InfoSection(
                title = "Description",
                icon = Icons.Default.Info,
                content = result.description
            )

            // Treatment section
            InfoSection(
                title = "Recommended Treatment",
                icon = Icons.Default.LocalHospital,
                content = result.treatment,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            )

            // Prevention section
            InfoSection(
                title = "Prevention Tips",
                icon = Icons.Default.Shield,
                content = result.prevention,
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
            )

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Get expert help */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Support, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Get Expert Help")
                }

                Button(
                    onClick = { /* Scan another */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Scan Another")
                }
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
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2
            )
        }
    }
}