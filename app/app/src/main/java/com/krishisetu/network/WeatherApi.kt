package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class WeatherInfo(val date: String, val forecast: String, val temperature: Double, val humidity: Double)

interface WeatherApi {
    @GET("/api/weather/")
    suspend fun getWeather(): Response<List<WeatherInfo>>
}
