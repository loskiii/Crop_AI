package com.jkuat.cropdiagnosis.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class DiagnosisHistoryItem(val date: String, val crop: String, val disease: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateBack: () -> Unit,
    historyList: List<DiagnosisHistoryItem> = listOf(
        DiagnosisHistoryItem("2025-06-21", "Tomato", "Late Blight"),
        DiagnosisHistoryItem("2025-06-20", "Maize", "Gray Leaf Spot")
    )
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Scan History") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(historyList) { item ->
                Card {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("🗓 Date: ${item.date}", style = MaterialTheme.typography.bodyMedium)
                        Text("🌿 Crop: ${item.crop}", style = MaterialTheme.typography.bodyMedium)
                        Text("🦠 Disease: ${item.disease}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
