package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class Loan(val id: Int, val type: String, val status: String)
data class Insurance(val id: Int, val type: String, val status: String)

data class FinanceOverview(val loans: List<Loan>, val insurances: List<Insurance>)

interface FinanceApi {
    @GET("/api/finance/overview/")
    suspend fun getFinanceOverview(): Response<FinanceOverview>
}
