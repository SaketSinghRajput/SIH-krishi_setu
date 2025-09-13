package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Body
import retrofit2.Response

data class Profile(
    val name: String,
    val location: String,
    val soilDeviceId: String
)
data class UpdateProfileRequest(val name: String, val location: String, val soilDeviceId: String)
data class UpdateProfileResponse(val success: Boolean, val message: String)

interface ProfileApi {
    @GET("/api/profile/")
    suspend fun getProfile(): Response<Profile>

    @PUT("/api/profile/update/")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): Response<UpdateProfileResponse>
}
