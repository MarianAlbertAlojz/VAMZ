package com.example.fitnessflowapp.data.repository

import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.model.UserProfile

class UserProfileRepository(private val dao: UserProfileDao) {
    suspend fun saveProfile(profile: UserProfile) = dao.insertProfile(profile)
    suspend fun getProfile(): UserProfile? = dao.getProfile()
}
