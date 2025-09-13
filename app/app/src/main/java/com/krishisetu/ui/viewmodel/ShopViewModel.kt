package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.ShopApi
import com.krishisetu.network.Product
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ShopState {
    object Loading : ShopState()
    data class Success(val products: List<Product>) : ShopState()
    data class Error(val message: String) : ShopState()
}

class ShopViewModel : ViewModel() {
    private val _state = MutableStateFlow<ShopState>(ShopState.Loading)
    val state: StateFlow<ShopState> = _state

    private val api = RetrofitInstance.retrofit.create(ShopApi::class.java)

    fun fetchProducts() {
        _state.value = ShopState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Product>> = api.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = ShopState.Success(response.body()!!)
                } else {
                    _state.value = ShopState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = ShopState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
