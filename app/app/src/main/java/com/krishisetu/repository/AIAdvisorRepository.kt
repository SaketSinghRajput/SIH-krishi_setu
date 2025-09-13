package com.krishisetu.repository

import com.krishisetu.model.AIAdvice
import com.krishisetu.network.AIAdvisorApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AIAdvisorRepository(private val api: AIAdvisorApiService) {
    suspend fun getAIAdvice(): AIAdvice = withContext(Dispatchers.IO) {
        api.getAdvice() // Corrected to use getAdvice()
    }
}
