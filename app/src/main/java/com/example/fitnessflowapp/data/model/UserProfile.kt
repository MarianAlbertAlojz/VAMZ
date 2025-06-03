package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val gender: Gender?,
    val age: Int?,
    val weight: Int?,
    val height: Int?,
    val goal: Goal?,
    val activityLevel: ActivityLevel?,
    val fullName: String?,
    val nickname: String?,
    val email: String?,
    val phone: String?,
    val avatarUri: String?
)
