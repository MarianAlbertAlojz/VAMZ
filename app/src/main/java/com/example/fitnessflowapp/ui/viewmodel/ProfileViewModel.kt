package com.example.fitnessflowapp.ui.viewmodel

import AppDatabase
import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessflowapp.data.model.UserProfile
import kotlinx.coroutines.launch

class ProfileViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val userProfileDao = AppDatabase.getInstance(app).userProfileDao()

    private val _userProfile = mutableStateOf<UserProfile?>(null)
    val userProfile: State<UserProfile?> = _userProfile

    init {
        viewModelScope.launch {
            _userProfile.value = userProfileDao.getProfile()
        }
    }
}
