package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.ProfileApi
import com.krishisetu.network.Profile
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val profile: Profile) : ProfileState()
    data class Error(val message: String) : ProfileState()
}

class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state: StateFlow<ProfileState> = _state

    private val api = RetrofitInstance.retrofit.create(ProfileApi::class.java)

    fun fetchProfile() {
        _state.value = ProfileState.Loading
        viewModelScope.launch {
            try {
                val response: Response<Profile> = api.getProfile()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = ProfileState.Success(response.body()!!)
                } else {
                    _state.value = ProfileState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = ProfileState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
