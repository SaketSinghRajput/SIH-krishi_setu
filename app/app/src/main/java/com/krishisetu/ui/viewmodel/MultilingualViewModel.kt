package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.MultilingualApi
import com.krishisetu.network.SmsRequest
import com.krishisetu.network.SmsResponse
import com.krishisetu.network.IvrRequest
import com.krishisetu.network.IvrResponse
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class MultilingualState {
    object Idle : MultilingualState()
    object Loading : MultilingualState()
    data class SmsSuccess(val message: String) : MultilingualState()
    data class IvrSuccess(val message: String) : MultilingualState()
    data class Error(val message: String) : MultilingualState()
}

class MultilingualViewModel : ViewModel() {
    private val _state = MutableStateFlow<MultilingualState>(MultilingualState.Idle)
    val state: StateFlow<MultilingualState> = _state

    private val api = RetrofitInstance.retrofit.create(MultilingualApi::class.java)

    fun sendSms(message: String, language: String) {
        _state.value = MultilingualState.Loading
        viewModelScope.launch {
            try {
                val response: Response<SmsResponse> = api.sendSms(SmsRequest(message, language))
                if (response.isSuccessful && response.body() != null && response.body()!!.success) {
                    _state.value = MultilingualState.SmsSuccess(response.body()!!.message)
                } else {
                    _state.value = MultilingualState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = MultilingualState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun requestIvr(infoType: String, language: String) {
        _state.value = MultilingualState.Loading
        viewModelScope.launch {
            try {
                val response: Response<IvrResponse> = api.requestIvr(IvrRequest(infoType, language))
                if (response.isSuccessful && response.body() != null && response.body()!!.success) {
                    _state.value = MultilingualState.IvrSuccess(response.body()!!.message)
                } else {
                    _state.value = MultilingualState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = MultilingualState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
