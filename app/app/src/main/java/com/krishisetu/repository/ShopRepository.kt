package com.krishisetu.repository

import com.krishisetu.model.Product
import com.krishisetu.network.ShopApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShopRepository(private val api: ShopApiService) {
    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        api.getProducts()
    }
}
