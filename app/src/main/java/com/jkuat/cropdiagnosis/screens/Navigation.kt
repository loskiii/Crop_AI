package com.jkuat.cropdiagnosis.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CropDiagnosisNavigation(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigate = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onNavigateToCamera = { navController.navigate("camera") },
                onNavigateToHistory = { navController.navigate("history") },
                onNavigateToGuide = { navController.navigate("guide") },
                onNavigateToCropPicker = { navController.navigate("crop_picker") }
            )
        }

        composable("camera") {
            CameraScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToResult = { resultId: String ->
                    navController.navigate("result/$resultId")
                }
            )
        }

        composable("history") {
            HistoryScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable("guide") {
            GuideScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable("result/{resultId}") { backStackEntry ->
            val resultId = backStackEntry.arguments?.getString("resultId") ?: ""
            ResultScreen(
                resultId = resultId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("tool_selector/{cropName}") { backStackEntry ->
            val crop = backStackEntry.arguments?.getString("cropName") ?: ""
            ToolSelectorScreen(
                cropName = crop,
                onUseInternalTool = { navController.navigate("camera") },
                onOpenExternalLink = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }

        composable("crop_picker") {
            CropPickerScreen(
                crops = listOf(
                    "Maize", "Tomato", "Beans", "Potato",
                    "Wheat", "Cassava", "Banana", "Kale",
                    "Rice", "Sugarcane"
                ),
                onCropSelected = { crop ->
                    navController.navigate("tool_selector/$crop")
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
