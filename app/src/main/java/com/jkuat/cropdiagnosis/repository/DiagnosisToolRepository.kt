package com.jkuat.cropdiagnosis.repository

import com.jkuat.cropdiagnosis.models.DiagnosisTool

object DiagnosisToolRepository {
    val diagnosisTools = listOf(
        DiagnosisTool(
            name = "Plantix",
            description = "Plantix supports a wide range of crops and provides AI-based disease detection via photos.",
            type = "Mobile App",
            platformLink = "https://play.google.com/store/apps/details?id=com.peat.GartenBank",
            cropsSupported = listOf("Tomato", "Maize", "Potato", "Beans", "Wheat", "Rice"),
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4e/Plantix_logo.png"
        ),
        DiagnosisTool(
            name = "PlantVillage Nuru",
            description = "An AI system by FAO and Penn State, mainly for maize, cassava, and potato.",
            type = "Mobile App",
            platformLink = "https://plantvillage.psu.edu/nuru",
            cropsSupported = listOf("Maize", "Cassava", "Potato"),
            imageUrl = "https://plantvillage.psu.edu/img/nuru-logo.png"
        ),
        DiagnosisTool(
            name = "Your App (Crop Diagnosis)",
            description = "Our lightweight offline-friendly tool for maize, tomato, and beans disease diagnosis.",
            type = "Your App",
            platformLink = "",
            cropsSupported = listOf("Maize", "Tomato", "Beans"),
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2950/2950745.png"
        ),
        DiagnosisTool(
            name = "AgroAI",
            description = "Offers real-time pest and disease detection for a variety of crops using smartphone cameras.",
            type = "Mobile App",
            platformLink = "https://www.agroai.io",
            cropsSupported = listOf("Maize", "Rice", "Wheat", "Sugarcane"),
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2909/2909833.png"
        ),
        DiagnosisTool(
            name = "Tanzania Plant Health App",
            description = "Designed by Tanzania's Ministry of Agriculture for common East African crops.",
            type = "Mobile App",
            platformLink = "https://play.google.com/store/apps/details?id=com.planthealth.tanzania",
            cropsSupported = listOf("Cassava", "Maize", "Banana", "Beans"),
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2178/2178648.png"
        ),
        DiagnosisTool(
            name = "Crop Doctor by CABI",
            description = "Online web-based diagnosis tool developed by CABI for identifying diseases and pests.",
            type = "Web App",
            platformLink = "https://www.plantwise.org/diagnosis-tool",
            cropsSupported = listOf("Tomato", "Beans", "Maize", "Wheat", "Potato", "Banana"),
            imageUrl = "https://www.plantwise.org/favicon.ico"
        ),
        DiagnosisTool(
            name = "AI-CropDoctor Kenya",
            description = "Kenyan-based AI platform that offers image-based diagnosis of crop diseases.",
            type = "Web App",
            platformLink = "https://ai-cropdoctor.co.ke",
            cropsSupported = listOf("Tomato", "Maize", "Kale", "Beans"),
            imageUrl = "https://cdn-icons-png.flaticon.com/512/3577/3577429.png"
        )
    )
}
