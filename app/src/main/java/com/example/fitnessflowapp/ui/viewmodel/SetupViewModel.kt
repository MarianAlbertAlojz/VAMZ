package com.example.fitnessflowapp.ui.viewmodel

import UserProfileRepository
import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessflowapp.data.model.Gender
import com.example.fitnessflowapp.data.model.Goal
import com.example.fitnessflowapp.data.model.UserProfile
import com.example.fitnessflowapp.ui.setup.ActivityLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ProfileField { FullName, Nickname, Email, Phone } //toto premysliet kde to dat

data class ProfileFormState(
    val avatarUri: Uri?,
    val fullName: String,
    val nickname: String,
    val email: String,
    val mobileNumber: String
)

data class SetupUiState(
    val gender: Gender? = null,
    val age: Int = 28,
    val weight: Int = 80,
    val height: Int = 80,
    val goal: Goal? = null,
    val activityLevel: ActivityLevel? = null,
    val avatarUri: String? = null,
    val fullName: String = "",
    val nickname: String = "",
    val email: String = "",
    val mobileNumber: String = ""
)

// Musel som použiť AndroidViewModel, pretože obyčajný ViewModel nemá prístup k Application kontextu.
// Vďaka AndroidViewModel(getApplication()) môžem zavolať UserProfileRepository.getInstance(application),
// aby som získal singletonovou inštanciu repozitára bez potreby DI frameworku.
class SetupViewModel(
    app: Application
) : AndroidViewModel(app) {
    private val repository = UserProfileRepository.getInstance(app)

    private val _uiState = MutableStateFlow(SetupUiState())
    val uiState: StateFlow<SetupUiState> = _uiState.asStateFlow()

    val formState: StateFlow<ProfileFormState> = uiState
        .map { s ->
            ProfileFormState(
                avatarUri = s.avatarUri?.let(Uri::parse),
                fullName = s.fullName,
                nickname = s.nickname,
                email = s.email,
                mobileNumber = s.mobileNumber
            )
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ProfileFormState(null, "", "", "", ""))

    fun updateProfileField(field: ProfileField, value: String) {
        _uiState.value = when (field) {
            ProfileField.FullName -> _uiState.value.copy(fullName = value)
            ProfileField.Nickname -> _uiState.value.copy(nickname = value)
            ProfileField.Email -> _uiState.value.copy(email = value)
            ProfileField.Phone -> _uiState.value.copy(mobileNumber = value)
        }
    }

    fun updateGender(gender: Gender) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun updateAge(age: Int) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun updateWeight(weight: Int) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun updateHeight(height: Int) {
        _uiState.value = _uiState.value.copy(height = height)
    }

    fun updateGoal(goal: Goal) {
        _uiState.value = _uiState.value.copy(goal = goal)
    }

    fun updateActivityLevel(activityLevel: ActivityLevel) {
        _uiState.value = _uiState.value.copy(activityLevel = activityLevel)
    }

    fun updateAvatarUri(uri: Uri) {
        _uiState.value = _uiState.value.copy(avatarUri = uri.toString())
    }


    fun saveAllAndFinish() = viewModelScope.launch {
        val profileValues = _uiState.value
        val profile = UserProfile(
            id = 0,
            gender = profileValues.gender,
            age = profileValues.age,
            weight = profileValues.weight,
            height = profileValues.height,
            goal = profileValues.goal,
            activityLevel = profileValues.activityLevel,
            avatarUri = profileValues.avatarUri,
            fullName = profileValues.fullName,
            nickname = profileValues.nickname,
            email = profileValues.email,
            phone = profileValues.mobileNumber
        )
        Log.d("DEBUG_PROFILE", "gender=${profileValues.gender}, goal=${profileValues.goal}, level=${profileValues.activityLevel}")
        repository.saveProfile(profile)
       /* try {


        } catch (e: Exception) {
            Log.e("SaveProfile", "Failed", e)
            //dako hodit na error screen
        }
        val saved = repository.getProfile()
        Log.d("ProfileCheck", "Profile = $saved")*/
    }
}
