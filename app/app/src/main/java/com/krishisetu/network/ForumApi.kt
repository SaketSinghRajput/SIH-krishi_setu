package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class ForumPost(val id: Int, val author: String, val content: String)
data class NewPostRequest(val content: String)
data class NewPostResponse(val success: Boolean, val message: String)

data class Comment(val id: Int, val postId: Int, val author: String, val content: String)
data class NewCommentRequest(val postId: Int, val content: String)
data class NewCommentResponse(val success: Boolean, val message: String)

interface ForumApi {
    @GET("/api/forum/posts/")
    suspend fun getPosts(): Response<List<ForumPost>>

    @POST("/api/forum/posts/new/")
    suspend fun addPost(@Body request: NewPostRequest): Response<NewPostResponse>

    @GET("/api/forum/comments/")
    suspend fun getComments(): Response<List<Comment>>

    @POST("/api/forum/comments/new/")
    suspend fun addComment(@Body request: NewCommentRequest): Response<NewCommentResponse>
}
