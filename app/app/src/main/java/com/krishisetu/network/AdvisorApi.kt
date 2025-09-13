package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class AdviceRequest(val imageUrl: String)
data class AdviceResponse(
    val bestCrops: List<String>,
    val fertilizerDosage: String,
    val pesticideAdvice: String
)

interface AdvisorApi {
    @POST("/api/advisor/predict/")
    suspend fun getAdvice(@Body request: AdviceRequest): Response<AdviceResponse>

    @GET("/api/advisor/history/")
    suspend fun getAdviceHistory(): Response<List<AdviceResponse>>
}
