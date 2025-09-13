package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle // Added this import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.krishisetu.model.Profile

@Composable
fun ProfileScreen() {
    var profile by remember {
        mutableStateOf(Profile("Farmer Name", "Village, District", "12345"))
    }
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(profile.name) }
    var location by remember { mutableStateOf(profile.location) }
    var soilDeviceId by remember { mutableStateOf(profile.soilDeviceId) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Large Avatar
        Spacer(Modifier.height(32.dp))
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Profile",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(96.dp)
        )
        Spacer(Modifier.height(16.dp))
        // Card with profile info
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Profile", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(16.dp))
                if (isEditing) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = { Text("Location") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = soilDeviceId,
                        onValueChange = { soilDeviceId = it },
                        label = { Text("Soil Device ID") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            profile = Profile(name, location, soilDeviceId)
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Save", color = MaterialTheme.colorScheme.onPrimary)
                    }
                } else {
                    Text("Name: ${profile.name}", style = MaterialTheme.typography.titleMedium)
                    Text("Location: ${profile.location}", style = MaterialTheme.typography.bodyLarge)
                    Text("Soil Device ID: ${profile.soilDeviceId}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { isEditing = true },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Edit Profile", color = MaterialTheme.colorScheme.onSecondary)
                    }
                }
            }
        }
    }
}
