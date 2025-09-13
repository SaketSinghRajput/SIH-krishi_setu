package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class Notification(val id: Int, val message: String, val type: String)

interface NotificationApi {
    @GET("/api/notifications/")
    suspend fun getNotifications(): Response<List<Notification>>
}
