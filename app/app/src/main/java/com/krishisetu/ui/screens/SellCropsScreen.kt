package com.krishisetu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture // Added import
import androidx.compose.material.icons.filled.Sell // Added import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.krishisetu.model.CropListing

@Composable
fun SellCropsScreen() {
    var cropName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var listings by remember {
        mutableStateOf(
            listOf(
                CropListing(1, "Wheat", 100.0, 2000.0),
                CropListing(2, "Rice", 50.0, 1200.0)
            )
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
                Icon(imageVector = Icons.Filled.Agriculture, contentDescription = "Sell Crops", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(40.dp))
                Spacer(Modifier.width(12.dp))
                Text("Sell Crops", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
        Spacer(Modifier.height(24.dp))
        // Crop Listing Form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(Modifier.padding(24.dp)) {
                OutlinedTextField(
                    value = cropName,
                    onValueChange = { cropName = it },
                    label = { Text("Crop Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price (₹)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (cropName.isNotBlank() && quantity.isNotBlank() && price.isNotBlank()) {
                            listings = listings + CropListing(
                                id = listings.size + 1,
                                name = cropName,
                                quantity = quantity.toDoubleOrNull() ?: 0.0,
                                price = price.toDoubleOrNull() ?: 0.0
                            )
                            cropName = ""
                            quantity = ""
                            price = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Filled.Sell, contentDescription = "List Crop", tint = MaterialTheme.colorScheme.onPrimary)
                    Spacer(Modifier.width(8.dp))
                    Text("List Crop for Sale", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Text("Your Listings", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
        listings.forEach { listing ->
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
                    Icon(imageVector = Icons.Filled.Sell, contentDescription = "Crop", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                    Spacer(Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text(listing.name, style = MaterialTheme.typography.titleMedium)
                        Text("${listing.quantity}kg - ₹${listing.price}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
                    }
                    Button(
                        onClick = { /* TODO: Edit */ },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Edit", color = MaterialTheme.colorScheme.onSecondary)
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick = {
                            listings = listings.filter { it.id != listing.id }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.onError)
                    }
                }
            }
        }
    }
}
