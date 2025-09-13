package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language // Added this import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultilingualScreen(viewModel: com.krishisetu.ui.viewmodel.MultilingualViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    var message by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("English") }
    val languages = listOf("English", "Hindi", "Marathi", "Bengali")
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
                text = "Multilingual SMS & Voice Access",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        // Card
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
                Icon(imageVector = Icons.Filled.Language, contentDescription = "Language", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text("Supported Languages:", style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    // Language Selector
                    Row {
                        languages.forEach { lang ->
                            Button(
                                onClick = { language = lang },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (language == lang) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                                ),
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text(lang, color = if (language == lang) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(value = message, onValueChange = { message = it }, label = { Text("Message") })
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { viewModel.sendSms(message, language) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Send SMS", color = MaterialTheme.colorScheme.onPrimary)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { viewModel.requestIvr("Info", language) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Request IVR Info", color = MaterialTheme.colorScheme.onSecondary)
        }
        Spacer(Modifier.height(24.dp))
        when (state) {
            is com.krishisetu.ui.viewmodel.MultilingualState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.MultilingualState.SmsSuccess -> Text((state as com.krishisetu.ui.viewmodel.MultilingualState.SmsSuccess).message, color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.MultilingualState.IvrSuccess -> Text((state as com.krishisetu.ui.viewmodel.MultilingualState.IvrSuccess).message, color = MaterialTheme.colorScheme.primary)
            is com.krishisetu.ui.viewmodel.MultilingualState.Error -> Text((state as com.krishisetu.ui.viewmodel.MultilingualState.Error).message, color = MaterialTheme.colorScheme.error)
            else -> {}
        }
    }
}
