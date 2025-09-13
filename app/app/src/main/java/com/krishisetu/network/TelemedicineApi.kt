package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class Appointment(val id: Int, val doctor: String, val time: String)
data class BookAppointmentRequest(val doctor: String, val time: String)
data class BookAppointmentResponse(val success: Boolean, val message: String)

interface TelemedicineApi {
    @GET("/api/telemedicine/appointments/")
    suspend fun getAppointments(): Response<List<Appointment>>

    @POST("/api/telemedicine/book/")
    suspend fun bookAppointment(@Body request: BookAppointmentRequest): Response<BookAppointmentResponse>
}
