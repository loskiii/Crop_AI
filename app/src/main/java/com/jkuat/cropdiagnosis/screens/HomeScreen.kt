package com.jkuat.cropdiagnosis.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToCamera: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToGuide: () -> Unit,
    onNavigateToCropPicker: () -> Unit // 🔥 Added this
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 🌱 Header Section with Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 40.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "🌱 Crop Doctor",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "AI-powered crop disease diagnosis",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                    )
                )
            }
        }

        // 🚀 Quick Actions
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                "Quick Actions",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuickActionCard(
                    title = "Scan Crop",
                    subtitle = "Take photo & diagnose",
                    icon = Icons.Default.CameraAlt,
                    onClick = onNavigateToCamera,
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    title = "History",
                    subtitle = "View past scans",
                    icon = Icons.Default.History,
                    onClick = onNavigateToHistory,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            QuickActionCard(
                title = "Disease Guide",
                subtitle = "Learn about crop diseases",
                icon = Icons.AutoMirrored.Filled.MenuBook,
                onClick = onNavigateToGuide,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🧠 NEW: Diagnosis Tools button
            Button(
                onClick = onNavigateToCropPicker,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🧠 Diagnosis Tools")
            }
        }

        // 🌾 Supported Crops
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = "Supported Crops",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(end = 24.dp)
            ) {
                items(getSupportedCrops()) { crop ->
                    CropCard(crop)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun QuickActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun CropCard(crop: CropInfo) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(140.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = crop.emoji, fontSize = 36.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = crop.name,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
                maxLines = 1
            )
            Text(
                text = "${crop.diseaseCount} diseases",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class CropInfo(
    val name: String,
    val emoji: String,
    val diseaseCount: Int
)

fun getSupportedCrops(): List<CropInfo> = listOf(
    CropInfo("Maize", "🌽", 5),
    CropInfo("Tomato", "🍅", 8),
    CropInfo("Beans", "🫘", 4),
    CropInfo("Potato", "🥔", 6),
    CropInfo("Wheat", "🌾", 3)
)
