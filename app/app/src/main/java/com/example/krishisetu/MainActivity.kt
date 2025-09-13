package com.example.krishisetu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.krishisetu.ui.theme.KrishisetuTheme // Your app theme
import com.krishisetu.ui.KrishiSetuAppScaffold // Import the scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KrishisetuTheme { // Apply your app's theme
                KrishiSetuAppScaffold() // Call your main UI scaffold
            }
        }
    }
}