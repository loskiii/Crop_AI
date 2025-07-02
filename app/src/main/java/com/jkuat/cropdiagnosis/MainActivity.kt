package com.jkuat.cropdiagnosis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jkuat.cropdiagnosis.screens.CropDiagnosisNavigation
import com.jkuat.cropdiagnosis.ui.theme.CropDiagnosisTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

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
        val navController = rememberNavController()
        val isLoading = remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(2000)
            isLoading.value = false
        }

        if (isLoading.value) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            CropDiagnosisNavigation(navController)
        }
    }
}
