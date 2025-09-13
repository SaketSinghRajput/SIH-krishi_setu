package com.krishisetu.network

import com.krishisetu.model.Profile
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ProfileApiService {
    @GET("/api/users/farmers/")
    suspend fun getProfile(): Profile

    @POST("/api/users/farmers/{id}/")
    suspend fun updateProfile(@Body profile: Profile)
}
