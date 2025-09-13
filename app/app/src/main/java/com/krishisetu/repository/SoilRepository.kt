package com.krishisetu.repository

import com.krishisetu.model.SoilData
import com.krishisetu.network.SoilApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SoilRepository(private val api: SoilApiService) {
    suspend fun getLatestSoilData(): SoilData = withContext(Dispatchers.IO) {
        api.getLatestSoilData()
    }
}
