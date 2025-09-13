package com.krishisetu.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double
)
data class CartRequest(val productIds: List<Int>)
data class CartResponse(val items: List<Product>, val total: Double)

data class OrderRequest(val cart: CartRequest)
data class OrderResponse(val orderId: Int, val status: String)

interface ShopApi {
    @GET("/api/shop/products/")
    suspend fun getProducts(): Response<List<Product>>

    @POST("/api/shop/cart/")
    suspend fun getCart(@Body request: CartRequest): Response<CartResponse>

    @POST("/api/shop/order/")
    suspend fun placeOrder(@Body request: OrderRequest): Response<OrderResponse>
}
