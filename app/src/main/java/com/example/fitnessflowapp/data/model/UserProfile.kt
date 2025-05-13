package com.example.fitnessflowapp.data.model

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 0,
    val gender: Gender?,
    val age: Int?,
    val weight: Int?,
    val height: Int?,
    val goal: String?,
    val activityLevel: String?,
    val fullName: String?,
    val nickname: String?,
    val email: String?,
    val phone: String?
)
