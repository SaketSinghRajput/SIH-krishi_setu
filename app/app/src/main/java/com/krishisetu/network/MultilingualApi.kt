package com.krishisetu.network

import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class SmsRequest(val message: String, val language: String)
data class SmsResponse(val success: Boolean, val message: String)

data class IvrRequest(val infoType: String, val language: String)
data class IvrResponse(val success: Boolean, val message: String)

interface MultilingualApi {
    @POST("/api/multilingual/sms/")
    suspend fun sendSms(@Body request: SmsRequest): Response<SmsResponse>

    @POST("/api/multilingual/ivr/")
    suspend fun requestIvr(@Body request: IvrRequest): Response<IvrResponse>
}
