package com.krishisetu.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
// import androidx.compose.material.icons.filled.Science // Unused
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
// import androidx.compose.ui.geometry.Offset // Unused
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme

// Main Composable function for the Dashboard Screen
@Composable
fun DashboardScreen() {
    Scaffold(
        // backgroundColor = Color(0xFFF5F5F5), // Material 3 Scaffold uses theme background
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { padding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Apply background to Column if needed
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Soil Dashboard",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            SoilStatsCard()
            Spacer(modifier = Modifier.height(24.dp))
            AlertsSection()
            Spacer(modifier = Modifier.height(24.dp))
            FeatureGrid()
        }
    }
}

// Composable for the Top Application Bar
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        // elevation is not a direct parameter in M3 TopAppBar, control via shadow modifier or surface tint
        actions = {
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.DarkGray)
            }
            IconButton(onClick = { /* Handle weather click */ }) {
                Icon(Icons.Default.WbCloudy, contentDescription = "Weather", tint = Color.DarkGray)
            }
        }
    )
}

// Composable for the Soil Statistics Card
@Composable
fun SoilStatsCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)), // Light green background
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem("pH", "6.5")
                StatItem("Moisture content", "60%")
                StatItem("NPK ratio", "10-5-8")
            }
            Spacer(modifier = Modifier.height(16.dp))
            SoilMoistureGraph()
        }
    }
}

// Composable for a single statistic item
@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

// Composable for the simulated soil moisture graph
@Composable
fun SoilMoistureGraph() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val path = Path().apply {
            moveTo(0f, size.height * 0.6f)
            cubicTo(size.width * 0.1f, size.height * 0.4f, size.width * 0.2f, size.height * 0.8f, size.width * 0.3f, size.height * 0.7f)
            cubicTo(size.width * 0.4f, size.height * 0.6f, size.width * 0.5f, size.height * 0.4f, size.width * 0.6f, size.height * 0.5f)
            cubicTo(size.width * 0.7f, size.height * 0.6f, size.width * 0.8f, size.height * 0.3f, size.width * 0.9f, size.height * 0.5f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(
            path = path,
            color = Color(0xFFA5D6A7) // Lighter green fill
        )
        drawPath(
            path = path,
            color = Color(0xFF66BB6A), // Darker green stroke
            style = Stroke(width = 4f)
        )
    }
}

// Composable for the Alerts and Reminders section
@Composable
fun AlertsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Alerts and Reminders", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "Time to irrigate!", fontSize = 16.sp, color = Color.Gray)
        }
        Button(
            onClick = { /* Handle details click */ },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A)) // Darker green button
        ) {
            Text(text = "View Details", color = Color.White)
        }
    }
}

// Composable for the grid of feature buttons
@Composable
fun FeatureGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { FeatureButton("Soil Detection", Icons.Default.Spa) }
        item { FeatureButton("Fertilizer Guide", Icons.Outlined.Grain) }
        item { FeatureButton("AI Assistant", Icons.Outlined.SmartToy) }
        item { FeatureButton("Market Place", Icons.Default.ShoppingBag) }
        item { FeatureButton("Knowledge Hub", Icons.Outlined.Book) }
        item { Spacer(modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE8F5E9))) } // Placeholder for the 6th item
    }
}

// Composable for a single feature button in the grid
@Composable
fun FeatureButton(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE8F5E9))
            .padding(8.dp)
    ) {
        Icon(icon, contentDescription = label, tint = Color.DarkGray, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 12.sp, color = Color.DarkGray, textAlign = TextAlign.Center)
    }
}

// Composable for the Bottom Navigation Bar
@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color(0xFFC8E6C9), // Very light green background
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = true,
            onClick = { /* Handle home click */ },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF388E3C), // Dark green for selected item
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.PieChart, contentDescription = "Dashboard") },
            selected = false,
            onClick = { /* Handle dashboard click */ },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF388E3C),
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Market") },
            selected = false,
            onClick = { /* Handle market click */ },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF388E3C),
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = false,
            onClick = { /* Handle profile click */ },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF388E3C),
                unselectedIconColor = Color.Gray
            )
        )
    }
}

// Preview for the DashboardScreen
@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun DefaultPreview() {
    MaterialTheme { // Added MaterialTheme for M3 components preview
        DashboardScreen()
    }
}
