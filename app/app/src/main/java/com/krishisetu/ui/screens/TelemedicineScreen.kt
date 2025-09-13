package com.krishisetu.ui.screens

import androidx.compose.foundation.background // Added this import
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices // Added import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TelemedicineScreen(viewModel: com.krishisetu.ui.viewmodel.TelemedicineViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.fetchAppointments() }
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
                text = "Telemedicine & Consultation",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        when (state) {
            is com.krishisetu.ui.viewmodel.TelemedicineState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.TelemedicineState.Success -> {
                (state as com.krishisetu.ui.viewmodel.TelemedicineState.Success).appointments.forEach { appt ->
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
                            Icon(imageVector = Icons.Filled.MedicalServices, contentDescription = "Telemedicine", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                            Spacer(Modifier.width(16.dp))
                            Text("${appt.doctor} - ${appt.time}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                            Button(
                                onClick = { /* TODO: Book appointment */ },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Text("Book", color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
            is com.krishisetu.ui.viewmodel.TelemedicineState.Error -> Text((state as com.krishisetu.ui.viewmodel.TelemedicineState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }
}