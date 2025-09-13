package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var idCode by remember { mutableStateOf("") }
    var isOtpMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = { isOtpMode = true }) { Text("Farmer (OTP)") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { isOtpMode = false }) { Text("Govt/NGO (ID)") }
        }
        Spacer(Modifier.height(16.dp))
        if (isOtpMode) {
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                label = { Text("OTP") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = onLoginSuccess, modifier = Modifier.fillMaxWidth()) {
                Text("Login as Farmer")
            }
        } else {
            OutlinedTextField(
                value = idCode,
                onValueChange = { idCode = it },
                label = { Text("Govt/NGO ID Code") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = onLoginSuccess, modifier = Modifier.fillMaxWidth()) {
                Text("Login as Govt/NGO")
            }
        }
    }
}
