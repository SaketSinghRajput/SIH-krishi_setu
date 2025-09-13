package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.TransportApi
import com.krishisetu.network.Transport
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class TransportState {
    object Loading : TransportState()
    data class Success(val transports: List<Transport>) : TransportState()
    data class Error(val message: String) : TransportState()
}

class TransportViewModel : ViewModel() {
    private val _state = MutableStateFlow<TransportState>(TransportState.Loading)
    val state: StateFlow<TransportState> = _state

    private val api = RetrofitInstance.retrofit.create(TransportApi::class.java)

    fun fetchTransportList() {
        _state.value = TransportState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Transport>> = api.getTransportList()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = TransportState.Success(response.body()!!)
                } else {
                    _state.value = TransportState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = TransportState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
