package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class SoilDataResponse(
    val ph: Double,
    val n: Int,
    val p: Int,
    val k: Int,
    val moisture: Double,
    val ec: Double,
    val status: String
)

interface DashboardApi {
    @GET("/api/dashboard/soil/")
    suspend fun getSoilData(): Response<SoilDataResponse>
}
