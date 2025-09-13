package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransportScreen(viewModel: com.krishisetu.ui.viewmodel.TransportViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.fetchTransportList() }
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
                text = "Logistics & Transport",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        when (state) {
            is com.krishisetu.ui.viewmodel.TransportState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.TransportState.Success -> {
                (state as com.krishisetu.ui.viewmodel.TransportState.Success).transports.forEach { t ->
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
                            Icon(imageVector = Icons.Filled.LocalShipping, contentDescription = "Transport", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                            Spacer(Modifier.width(16.dp))
                            Text(t.type, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                            Button(
                                onClick = { /* TODO: Book transport */ },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Text("Book", color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
            is com.krishisetu.ui.viewmodel.TransportState.Error -> Text((state as com.krishisetu.ui.viewmodel.TransportState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }
}
