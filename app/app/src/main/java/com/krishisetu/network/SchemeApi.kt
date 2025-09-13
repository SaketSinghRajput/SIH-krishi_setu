package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class Scheme(val id: Int, val name: String, val status: String)
data class ApplySchemeRequest(val schemeId: Int)
data class ApplySchemeResponse(val success: Boolean, val message: String)

interface SchemeApi {
    @GET("/api/schemes/list/")
    suspend fun getSchemes(): Response<List<Scheme>>

    @POST("/api/schemes/apply/")
    suspend fun applyScheme(@Body request: ApplySchemeRequest): Response<ApplySchemeResponse>
}
