package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class Wallet(val balance: Double, val transactions: List<String>)
data class AddFundsRequest(val amount: Double)
data class AddFundsResponse(val success: Boolean, val message: String)

interface WalletApi {
    @GET("/api/wallet/")
    suspend fun getWallet(): Response<Wallet>

    @POST("/api/wallet/add/")
    suspend fun addFunds(@Body request: AddFundsRequest): Response<AddFundsResponse>
}
