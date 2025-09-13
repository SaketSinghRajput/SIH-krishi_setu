package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons // Added this import
import androidx.compose.material.icons.filled.Lightbulb // Added this import if specific icon is used
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.krishisetu.model.AIAdvice

@Composable
fun AIAdvisorScreen() {
    // TODO: Replace with real repository call
    val advice = remember {
        AIAdvice(
            bestCrops = listOf("Wheat", "Rice"),
            fertilizerDosage = "50kg/acre",
            pesticideAdvice = "Use EcoSafe"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Lightbulb, contentDescription = "AI Advisor", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(40.dp))
                Spacer(Modifier.width(12.dp))
                Text("AI Advisor", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
        Spacer(Modifier.height(24.dp))
        // Advice Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(Modifier.padding(24.dp)) {
                Text("Best Crops: ${advice.bestCrops.joinToString(", ")}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text("Fertilizer Dosage: ${advice.fertilizerDosage}", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(8.dp))
                Text("Pesticide Advice: ${advice.pesticideAdvice}", style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { /* TODO: Chatbot */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Ask AI Chatbot", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
