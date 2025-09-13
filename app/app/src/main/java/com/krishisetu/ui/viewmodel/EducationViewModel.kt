package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishisetu.network.EducationApi
import com.krishisetu.network.Course
import com.krishisetu.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class EducationState {
    object Loading : EducationState()
    data class Success(val courses: List<Course>) : EducationState()
    data class Error(val message: String) : EducationState()
}

class EducationViewModel : ViewModel() {
    private val _state = MutableStateFlow<EducationState>(EducationState.Loading)
    val state: StateFlow<EducationState> = _state

    private val api = RetrofitInstance.retrofit.create(EducationApi::class.java)

    fun fetchCourses() {
        _state.value = EducationState.Loading
        viewModelScope.launch {
            try {
                val response: Response<List<Course>> = api.getCourses()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = EducationState.Success(response.body()!!)
                } else {
                    _state.value = EducationState.Error(response.message())
                }
            } catch (e: Exception) {
                _state.value = EducationState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
