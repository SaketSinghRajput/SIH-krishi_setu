package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.ForumApi
import com.krishisetu.network.ForumPost
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ForumState {
    object Loading : ForumState()
    data class Success(val posts: List<ForumPost>) : ForumState()
    data class Error(val message: String) : ForumState()
}

class ForumViewModel : ViewModel() {
    private val _state = MutableStateFlow<ForumState>(ForumState.Loading)
    val state: StateFlow<ForumState> = _state

    private val api = RetrofitInstance.retrofit.create(ForumApi::class.java)

    fun fetchPosts() {
        _state.value = ForumState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<ForumPost>> = api.getPosts()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = ForumState.Success(response.body()!!)
                } else {
                    _state.value = ForumState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = ForumState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
