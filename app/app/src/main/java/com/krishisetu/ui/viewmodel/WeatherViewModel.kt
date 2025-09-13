package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.WeatherApi
import com.krishisetu.network.WeatherInfo
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: List<WeatherInfo>) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class WeatherViewModel : ViewModel() {
    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state: StateFlow<WeatherState> = _state

    private val api = RetrofitInstance.retrofit.create(WeatherApi::class.java)

    fun fetchWeather() {
        _state.value = WeatherState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<WeatherInfo>> = api.getWeather()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = WeatherState.Success(response.body()!!)
                } else {
                    _state.value = WeatherState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = WeatherState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
