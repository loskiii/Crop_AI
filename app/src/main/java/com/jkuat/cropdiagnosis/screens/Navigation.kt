package com.jkuat.cropdiagnosis.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jkuat.cropdiagnosis.presentation.screens.ResultScreen


@Composable
fun CropDiagnosisNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToCamera = { navController.navigate("camera") },
                onNavigateToHistory = { navController.navigate("history") },
                onNavigateToGuide = { navController.navigate("guide") }
            )
        }

        composable("camera") {
            CameraScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToResult = { result ->
                    navController.navigate("result/$result")
                }
            )
        }

        composable("history") {
            HistoryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("guide") {
            GuideScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("result/{resultId}") { backStackEntry ->
            val resultId = backStackEntry.arguments?.getString("resultId") ?: ""
            ResultScreen(
                resultId = resultId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

//@Composable
//fun GuideScreen(onNavigateBack: () -> Boolean) {
//
//}
//
//@Composable
//fun HistoryScreen(onNavigateBack: () -> Boolean) {
//
//}
