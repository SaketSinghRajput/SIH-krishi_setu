package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.MarketApi
import com.krishisetu.network.CropListing
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class MarketState {
    object Loading : MarketState()
    data class Success(val listings: List<CropListing>) : MarketState()
    data class Error(val message: String) : MarketState()
}

class MarketViewModel : ViewModel() {
    private val _state = MutableStateFlow<MarketState>(MarketState.Loading)
    val state: StateFlow<MarketState> = _state

    private val api = RetrofitInstance.retrofit.create(MarketApi::class.java)

    fun fetchListings() {
        _state.value = MarketState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<CropListing>> = api.getListings()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = MarketState.Success(response.body()!!)
                } else {
                    _state.value = MarketState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = MarketState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
