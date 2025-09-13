package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class Transport(val id: Int, val type: String, val status: String)
data class BookTransportRequest(val type: String)
data class BookTransportResponse(val success: Boolean, val message: String)

interface TransportApi {
    @GET("/api/transport/list/")
    suspend fun getTransportList(): Response<List<Transport>>

    @POST("/api/transport/book/")
    suspend fun bookTransport(@Body request: BookTransportRequest): Response<BookTransportResponse>
}
