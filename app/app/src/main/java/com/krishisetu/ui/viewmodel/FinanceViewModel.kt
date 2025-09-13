package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.FinanceApi
import com.krishisetu.network.FinanceOverview
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class FinanceState {
    object Loading : FinanceState()
    data class Success(val overview: FinanceOverview) : FinanceState()
    data class Error(val message: String) : FinanceState()
}

class FinanceViewModel : ViewModel() {
    private val _state = MutableStateFlow<FinanceState>(FinanceState.Loading)
    val state: StateFlow<FinanceState> = _state

    private val api = RetrofitInstance.retrofit.create(FinanceApi::class.java)

    fun fetchFinanceOverview() {
        _state.value = FinanceState.Loading
        viewModelScope.launch {
            try {
                val response: Response<FinanceOverview> = api.getFinanceOverview()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = FinanceState.Success(response.body()!!)
                } else {
                    _state.value = FinanceState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = FinanceState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
