package com.krishisetu.ui.screens

import androidx.compose.foundation.background // Added this import
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons // Added this import if it wasn't there
import androidx.compose.material.icons.filled.Lightbulb // Added for clarity, though Icons.Filled.Lightbulb should work with just the above
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsScreen(viewModel: com.krishisetu.ui.viewmodel.AnalyticsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.fetchAnalytics() }
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
            Text(
                text = "Analytics & Insights",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        when (state) {
            is com.krishisetu.ui.viewmodel.AnalyticsState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.AnalyticsState.Success -> {
                val data = (state as com.krishisetu.ui.viewmodel.AnalyticsState.Success).data
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row(
                        Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lightbulb,
                            contentDescription = "Analytics",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(Modifier.width(16.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Yield Prediction: ${data.yieldPrediction}", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(8.dp))
                            Text("Subsidy: ₹${data.subsidy}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
            is com.krishisetu.ui.viewmodel.AnalyticsState.Error -> Text((state as com.krishisetu.ui.viewmodel.AnalyticsState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }
}
