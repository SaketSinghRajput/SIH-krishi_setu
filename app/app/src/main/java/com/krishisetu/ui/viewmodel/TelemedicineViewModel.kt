package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.TelemedicineApi
import com.krishisetu.network.Appointment
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class TelemedicineState {
    object Loading : TelemedicineState()
    data class Success(val appointments: List<Appointment>) : TelemedicineState()
    data class Error(val message: String) : TelemedicineState()
}

class TelemedicineViewModel : ViewModel() {
    private val _state = MutableStateFlow<TelemedicineState>(TelemedicineState.Loading)
    val state: StateFlow<TelemedicineState> = _state

    private val api = RetrofitInstance.retrofit.create(TelemedicineApi::class.java)

    fun fetchAppointments() {
        _state.value = TelemedicineState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Appointment>> = api.getAppointments()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = TelemedicineState.Success(response.body()!!)
                } else {
                    _state.value = TelemedicineState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = TelemedicineState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
