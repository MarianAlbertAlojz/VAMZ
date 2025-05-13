package com.example.fitnessflowapp.data.model

import androidx.annotation.DrawableRes

data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageBackground: Int,
    @DrawableRes val imageButton: Int
)