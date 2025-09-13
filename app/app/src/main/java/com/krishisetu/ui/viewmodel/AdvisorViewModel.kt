package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.AdvisorApi
import com.krishisetu.network.AdviceRequest
import com.krishisetu.network.AdviceResponse
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class AdvisorState {
    object Loading : AdvisorState()
    data class Success(val data: AdviceResponse) : AdvisorState()
    data class Error(val message: String) : AdvisorState()
}

class AdvisorViewModel : ViewModel() {
    private val _state = MutableStateFlow<AdvisorState>(AdvisorState.Loading)
    val state: StateFlow<AdvisorState> = _state

    private val api = RetrofitInstance.retrofit.create(AdvisorApi::class.java)

    fun fetchAdvice(imageUrl: String) {
        _state.value = AdvisorState.Loading
        viewModelScope.launch {
            try {
                val response: Response<AdviceResponse> = api.getAdvice(AdviceRequest(imageUrl))
                if (response.isSuccessful && response.body() != null) {
                    _state.value = AdvisorState.Success(response.body()!!)
                } else {
                    _state.value = AdvisorState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = AdvisorState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
