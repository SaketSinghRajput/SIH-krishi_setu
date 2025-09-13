package com.krishisetu.ui.screens

import androidx.compose.foundation.background // Added import
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store // Added import
import androidx.compose.material.icons.filled.ShoppingCart // Added import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.krishisetu.model.Product

@Composable
fun ShopScreen() {
    // TODO: Replace with real repository call
    val products = remember {
        listOf(
            Product(1, "Fertilizer A", "Fertilizers", 500.0),
            Product(2, "Pesticide B", "Pesticides", 300.0),
            Product(3, "Organic Manure", "Organic Manure", 250.0)
        )
    }
    var cart by remember { mutableStateOf(listOf<Product>()) }

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
                text = "Marketplace",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))
        // Product List
        products.forEach { product ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Store, contentDescription = "Product", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                    Spacer(Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text(product.name, style = MaterialTheme.typography.titleMedium)
                        Text(product.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                        Text("₹${product.price}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
                    }
                    Button(
                        onClick = { cart = cart + product },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Add", tint = MaterialTheme.colorScheme.onPrimary)
                        Spacer(Modifier.width(4.dp))
                        Text("Add", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        // Cart Summary
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(32.dp))
                Spacer(Modifier.width(16.dp))
                Text("Items in Cart: ${cart.size}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { /* TODO: Checkout */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Checkout", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}
