package com.krishisetu.ui.screens

import androidx.compose.foundation.background // Added this import
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star // Added this import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SchemeScreen(viewModel: com.krishisetu.ui.viewmodel.SchemeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.fetchSchemes() }
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
                text = "Government Schemes",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        when (state) {
            is com.krishisetu.ui.viewmodel.SchemeState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.SchemeState.Success -> {
                (state as com.krishisetu.ui.viewmodel.SchemeState.Success).schemes.forEach { scheme ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Row(
                            Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Filled.Star, contentDescription = "Scheme", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(scheme.name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                            Button(
                                onClick = { /* TODO: Apply for scheme */ },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Text("Apply", color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
            is com.krishisetu.ui.viewmodel.SchemeState.Error -> Text((state as com.krishisetu.ui.viewmodel.SchemeState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }
}
