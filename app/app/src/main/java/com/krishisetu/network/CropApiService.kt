package com.krishisetu.network

import com.krishisetu.model.CropListing
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Path

interface CropApiService {
    @GET("/api/market/listings/")
    suspend fun getListings(): List<CropListing>

    @POST("/api/market/listings/new/")
    suspend fun addListing(@Body listing: CropListing)

    @DELETE("/api/market/listings/{id}/")
    suspend fun deleteListing(@Path("id") id: Int)
}
