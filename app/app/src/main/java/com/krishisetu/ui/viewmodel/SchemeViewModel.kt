package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.SchemeApi
import com.krishisetu.network.Scheme
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class SchemeState {
    object Loading : SchemeState()
    data class Success(val schemes: List<Scheme>) : SchemeState()
    data class Error(val message: String) : SchemeState()
}

class SchemeViewModel : ViewModel() {
    private val _state = MutableStateFlow<SchemeState>(SchemeState.Loading)
    val state: StateFlow<SchemeState> = _state

    private val api = RetrofitInstance.retrofit.create(SchemeApi::class.java)

    fun fetchSchemes() {
        _state.value = SchemeState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Scheme>> = api.getSchemes()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = SchemeState.Success(response.body()!!)
                } else {
                    _state.value = SchemeState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = SchemeState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
