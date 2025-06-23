package com.jkuat.cropdiagnosis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.jkuat.cropdiagnosis.screens.CameraScreen
import com.jkuat.cropdiagnosis.ui.theme.CropDiagnosisTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CropDiagnosisApp()
        }
    }
}

@Composable
fun CropDiagnosisApp() {
    CropDiagnosisTheme {
        CameraScreen(
            onNavigateBack = { /* Handle back */ },
            onNavigateToResult = {
                // Handle result navigation
            }
        )
    }
}
