package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class AnalyticsData(val yieldPrediction: String, val subsidy: String)

interface AnalyticsApi {
    @GET("/api/analytics/")
    suspend fun getAnalytics(): Response<AnalyticsData>
}
