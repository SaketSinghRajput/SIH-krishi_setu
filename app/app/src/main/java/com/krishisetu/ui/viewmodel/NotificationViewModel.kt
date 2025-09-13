package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.NotificationApi
import com.krishisetu.network.Notification
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class NotificationState {
    object Loading : NotificationState()
    data class Success(val notifications: List<Notification>) : NotificationState()
    data class Error(val message: String) : NotificationState()
}

class NotificationViewModel : ViewModel() {
    private val _state = MutableStateFlow<NotificationState>(NotificationState.Loading)
    val state: StateFlow<NotificationState> = _state

    private val api = RetrofitInstance.retrofit.create(NotificationApi::class.java)

    fun fetchNotifications() {
        _state.value = NotificationState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Notification>> = api.getNotifications()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = NotificationState.Success(response.body()!!)
                } else {
                    _state.value = NotificationState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = NotificationState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
