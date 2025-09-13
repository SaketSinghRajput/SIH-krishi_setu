package com.krishisetu.network

import com.krishisetu.model.AIAdvice
import retrofit2.http.GET

interface AIAdvisorApiService {
    @GET("/api/advisor/predict/")
    suspend fun getAdvice(): AIAdvice
}
