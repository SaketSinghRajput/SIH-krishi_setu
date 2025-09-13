package com.krishisetu.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp


import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var loggedIn by remember { mutableStateOf(false) }
            if (!loggedIn) {
                com.krishisetu.ui.screens.LoginScreen(
                    onLoginSuccess = { loggedIn = true }
                )
            } else {
                KrishiSetuAppScaffoldWithNav(navController)
            }
        }
    }
}

@Composable
fun KrishiSetuAppScaffoldWithNav(navController: androidx.navigation.NavHostController) {
    // Only show the selected screen, no bottom navigation bar
    var selectedScreen by remember { mutableStateOf(KrishiSetuScreen.Dashboard) }
    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedScreen) {
            KrishiSetuScreen.Dashboard -> com.krishisetu.ui.screens.DashboardScreen()
            KrishiSetuScreen.AIAdvisor -> com.krishisetu.ui.screens.AIAdvisorScreen()
            KrishiSetuScreen.Shop -> com.krishisetu.ui.screens.ShopScreen()
            KrishiSetuScreen.SellCrops -> com.krishisetu.ui.screens.SellCropsScreen()
            KrishiSetuScreen.Orders -> com.krishisetu.ui.screens.OrdersScreen()
            KrishiSetuScreen.Notifications -> com.krishisetu.ui.screens.NotificationsScreen()
            KrishiSetuScreen.Help -> com.krishisetu.ui.screens.HelpScreen()
            KrishiSetuScreen.Settings -> com.krishisetu.ui.screens.SettingsScreen()
            KrishiSetuScreen.Profile -> com.krishisetu.ui.screens.ProfileScreen()
        }
    }
}

enum class KrishiSetuScreen(val label: String) {
    Dashboard("Dashboard"),
    AIAdvisor("AI Advisor"),
    Shop("Shop"),
    SellCrops("Sell Crops"),
    Orders("Orders"),
    Notifications("Notifications"),
    Help("Help"),
    Settings("Settings"),
    Profile("Profile")
}

@Composable
fun KrishiSetuAppScaffold() { // Renamed from MainScreen
    var selectedScreen by remember { mutableStateOf(KrishiSetuScreen.Dashboard) }

    Scaffold(
        bottomBar = {
            KrishiSetuBottomBar(selectedScreen) { selectedScreen = it }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedScreen) {
                KrishiSetuScreen.Dashboard -> com.krishisetu.ui.screens.DashboardScreen()
                KrishiSetuScreen.AIAdvisor -> com.krishisetu.ui.screens.AIAdvisorScreen()
                KrishiSetuScreen.Shop -> com.krishisetu.ui.screens.ShopScreen()
                KrishiSetuScreen.SellCrops -> com.krishisetu.ui.screens.SellCropsScreen()
                KrishiSetuScreen.Orders -> com.krishisetu.ui.screens.OrdersScreen()
                KrishiSetuScreen.Notifications -> com.krishisetu.ui.screens.NotificationsScreen()
                KrishiSetuScreen.Help -> com.krishisetu.ui.screens.HelpScreen()
                KrishiSetuScreen.Settings -> com.krishisetu.ui.screens.SettingsScreen()
                KrishiSetuScreen.Profile -> com.krishisetu.ui.screens.ProfileScreen()
            }
        }
    }
}

@Composable
fun KrishiSetuBottomBar(selected: KrishiSetuScreen, onSelect: (KrishiSetuScreen) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Dashboard,
            onClick = { onSelect(KrishiSetuScreen.Dashboard) },
            icon = {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Dashboard", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Home", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.AIAdvisor,
            onClick = { onSelect(KrishiSetuScreen.AIAdvisor) },
            icon = {
                Icon(imageVector = Icons.Filled.Lightbulb, contentDescription = "Advisory", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Advisory", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Shop,
            onClick = { onSelect(KrishiSetuScreen.Shop) },
            icon = {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Marketplace", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Market", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.SellCrops,
            onClick = { onSelect(KrishiSetuScreen.SellCrops) },
            icon = {
                Icon(imageVector = Icons.Filled.Sell, contentDescription = "Sell Crops", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Sell", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Orders,
            onClick = { onSelect(KrishiSetuScreen.Orders) },
            icon = {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Orders", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Orders", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Notifications,
            onClick = { onSelect(KrishiSetuScreen.Notifications) },
            icon = {
                Icon(imageVector = Icons.Filled.Lightbulb, contentDescription = "Notifications", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Alerts", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Help,
            onClick = { onSelect(KrishiSetuScreen.Help) },
            icon = {
                Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = "Help", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Help", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Settings,
            onClick = { onSelect(KrishiSetuScreen.Settings) },
            icon = {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Settings", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = selected == KrishiSetuScreen.Profile,
            onClick = { onSelect(KrishiSetuScreen.Profile) },
            icon = {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onPrimary)
            },
            label = { Text("Profile", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary) }
        )
    }
}
