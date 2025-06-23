package com.jkuat.cropdiagnosis.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Crop Diagnosis Guide") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("📸 How to Use the App", style = MaterialTheme.typography.titleMedium)
            Text("1. Click 'Scan Crop' to open the camera.")
            Text("2. Point the camera at the crop leaf and capture.")
            Text("3. Wait for the analysis and get diagnosis results.")
            Text("4. View treatment and prevention tips.")
        }
    }
}
