package com.krishisetu.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember // Added import
import com.krishisetu.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KrishiSetuApp()
        }
    }
}

@Composable
fun KrishiSetuApp() {
    var isLoggedIn by remember { mutableStateOf(false) }
    MaterialTheme {
        if (!isLoggedIn) {
            com.krishisetu.ui.screens.LoginScreen(onLoginSuccess = { isLoggedIn = true })
        } else {
            com.krishisetu.ui.screens.MainScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KrishiSetuAppPreview() {
    KrishiSetuApp()
}
