package com.krishisetu.network

import com.krishisetu.model.SoilData
import retrofit2.http.GET

interface SoilApiService {
    @GET("/api/dashboard/soil/")
    suspend fun getLatestSoilData(): SoilData
}
