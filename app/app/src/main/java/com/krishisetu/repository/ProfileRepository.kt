package com.krishisetu.repository

import com.krishisetu.model.Profile
import com.krishisetu.network.ProfileApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val api: ProfileApiService) {
    suspend fun getProfile(): Profile = withContext(Dispatchers.IO) {
        api.getProfile()
    }
    suspend fun updateProfile(profile: Profile) = withContext(Dispatchers.IO) {
        api.updateProfile(profile)
    }
}
