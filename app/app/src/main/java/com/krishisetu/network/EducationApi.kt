package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.Response

data class Course(val id: Int, val title: String, val progress: Double)

interface EducationApi {
    @GET("/api/education/courses/")
    suspend fun getCourses(): Response<List<Course>>
}
