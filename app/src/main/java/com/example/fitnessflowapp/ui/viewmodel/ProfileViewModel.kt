package com.example.fitnessflowapp.ui.viewmodel

import AppDatabase
import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.data.model.UserProfile
import kotlinx.coroutines.launch

//strings done
//komentare
class ProfileViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    private val userProfileDao = AppDatabase.getInstance(app).userProfileDao()

    private val _userProfile = mutableStateOf<UserProfile?>(null)
    val userProfile: State<UserProfile?> = _userProfile

    init {
        viewModelScope.launch {
            _userProfile.value = userProfileDao.getProfile()
        }
    }

    fun formatBmiSmart(bmi: Double): String {
        return if (bmi % 1.0 == 0.0) {
            bmi.toInt().toString()
        } else {
            String.format("%.1f", bmi)
        }
    }

    fun calculateBmi(): Double? {
        val profile = userProfile.value
        val height = profile?.height
        val weight = profile?.weight

        if (height != null && weight != null) {
            val heightInMeters = height / 100.0
            if (heightInMeters > 0) {
                return weight / (heightInMeters * heightInMeters)
            }
        }
        return null
    }

    fun bmiStatus(bmi: Double): String {
        return when {
            bmi < 18.5 -> app.getString(R.string.bmi_underweight)
            bmi < 25.0 -> app.getString(R.string.bmi_normal)
            bmi < 30.0 -> app.getString(R.string.bmi_overweight)
            else -> app.getString(R.string.bmi_obese)
        }
    }
}
