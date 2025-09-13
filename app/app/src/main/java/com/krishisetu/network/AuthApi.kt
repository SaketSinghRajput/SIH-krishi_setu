package com.krishisetu.network

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

// Data models

data class LoginRequest(val mobile: String, val otp: String)
data class LoginResponse(val token: String, val userType: String)
data class RegisterRequest(val mobile: String, val aadhaar: String, val kycDoc: String)
data class RegisterResponse(val success: Boolean, val message: String)

interface AuthApi {
    @POST("/api/auth/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api/auth/register/")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
