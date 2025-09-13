package com.krishisetu.repository

import com.krishisetu.model.CropListing
import com.krishisetu.network.CropApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CropRepository(private val api: CropApiService) {
    suspend fun getListings(): List<CropListing> = withContext(Dispatchers.IO) {
        api.getListings()
    }
    suspend fun addListing(listing: CropListing) = withContext(Dispatchers.IO) {
        api.addListing(listing)
    }
    suspend fun deleteListing(id: Int) = withContext(Dispatchers.IO) {
        api.deleteListing(id)
    }
}
