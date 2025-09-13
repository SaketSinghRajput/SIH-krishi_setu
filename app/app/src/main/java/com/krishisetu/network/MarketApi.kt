package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class CropListing(
    val id: Int,
    val cropName: String,
    val quantity: Double,
    val price: Double
)
data class NewListingRequest(val cropName: String, val quantity: Double, val price: Double)
data class NewListingResponse(val success: Boolean, val message: String)

interface MarketApi {
    @GET("/api/market/listings/")
    suspend fun getListings(): Response<List<CropListing>>

    @POST("/api/market/listings/new/")
    suspend fun addListing(@Body request: NewListingRequest): Response<NewListingResponse>
}
