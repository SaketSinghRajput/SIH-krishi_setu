package com.krishisetu.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Note: You would need to add an image resource for the grass background and the Google logo.
// I'll use placeholders for this example. R.drawable.google_logo and R.drawable.grass_background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        containerColor = Color(0xFFF0F4C3) // Light lime green background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Background grass image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(
                            Color(0xFF8BC34A), // Placeholder color
                            shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
                        )
                        .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp))
                ) {
                    // In a real app, you would use an Image composable here
                    // Image(
                    //     painter = painterResource(id = R.drawable.grass_background),
                    //     contentDescription = "Grass background",
                    //     contentScale = ContentScale.Crop,
                    //     modifier = Modifier.fillMaxSize()
                    // )
                }


                // Logo
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(100.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    // In a real app, you would use your app's logo
                    Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
                        Text("Logo", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    }
                }
            }

            // Login Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Log in to your account",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Phone Number Field
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Phone Number") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF8BC34A),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF8BC34A)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon = if (passwordHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                            val description = if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF8BC34A),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF8BC34A)
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Login Button
                Button(
                    onClick = { 
                        // TODO: Implement actual login logic
                        // After successful login, call onLoginSuccess to navigate
                        onLoginSuccess() 
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("LOGIN", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("or", color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))

                // Continue with Google Button
                OutlinedButton(
                    onClick = { /* Handle Google Sign In */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    // Replace with actual Google logo if available
                    // Icon(painter = painterResource(id = R.drawable.google_logo), contentDescription = "Google Logo", modifier = Modifier.size(24.dp))
                    Text("G", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 20.sp) // Placeholder
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Continue with google", color = Color.Black)
                }

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Text("Don't have an account", color = Color.Gray)
                    TextButton(onClick = { /* Navigate to Sign Up */ }) {
                        Text("Sign Up", color = Color(0xFF8BC34A), fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {}) // Consider wrapping with your app's theme for accurate preview
}
