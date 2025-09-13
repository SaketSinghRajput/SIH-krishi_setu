package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.DashboardApi
import com.krishisetu.network.RetrofitInstance
import com.krishisetu.network.SoilDataResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class DashboardState {
    object Loading : DashboardState()
    data class Success(val data: SoilDataResponse) : DashboardState()
    data class Error(val message: String) : DashboardState()
}

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow<DashboardState>(DashboardState.Loading)
    val state: StateFlow<DashboardState> = _state

    private val api = RetrofitInstance.retrofit.create(DashboardApi::class.java)

    fun fetchSoilData() {
        _state.value = DashboardState.Loading
        viewModelScope.launch {
            try {
                val response: Response<SoilDataResponse> = api.getSoilData()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = DashboardState.Success(response.body()!!)
                } else {
                    _state.value = DashboardState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = DashboardState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
