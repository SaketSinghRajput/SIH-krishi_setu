package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.AnalyticsApi
import com.krishisetu.network.AnalyticsData
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class AnalyticsState {
    object Loading : AnalyticsState()
    data class Success(val data: AnalyticsData) : AnalyticsState()
    data class Error(val message: String) : AnalyticsState()
}

class AnalyticsViewModel : ViewModel() {
    private val _state = MutableStateFlow<AnalyticsState>(AnalyticsState.Loading)
    val state: StateFlow<AnalyticsState> = _state

    private val api = RetrofitInstance.retrofit.create(AnalyticsApi::class.java)

    fun fetchAnalytics() {
        _state.value = AnalyticsState.Loading
        viewModelScope.launch {
            try {
                val response: Response<AnalyticsData> = api.getAnalytics()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = AnalyticsState.Success(response.body()!!)
                } else {
                    _state.value = AnalyticsState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = AnalyticsState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
