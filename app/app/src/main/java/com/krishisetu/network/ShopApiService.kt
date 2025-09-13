package com.krishisetu.network

import com.krishisetu.model.Product
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

// Define Cart data class
data class Cart(
    val items: List<Product>, // Example: list of products in the cart
    val userId: String // Example: user ID
)

// Define Order data class
data class Order(
    val cart: Cart, // Example: the cart being ordered
    val shippingAddress: String // Example: shipping address
)

interface ShopApiService {
    @GET("/api/shop/products/")
    suspend fun getProducts(): List<Product>

    @POST("/api/shop/cart/")
    suspend fun addToCart(@Body cart: Cart)

    @POST("/api/shop/order/")
    suspend fun placeOrder(@Body order: Order)
}
