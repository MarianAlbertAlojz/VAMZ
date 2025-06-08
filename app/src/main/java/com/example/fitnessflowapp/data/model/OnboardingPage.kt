package com.example.fitnessflowapp.data.model

import androidx.annotation.DrawableRes

/**
 * data trieda reprezentujuca model pre onboarding obrazovku
 *
 * @param title hlavny nadpis
 * @param description doplnkovy text
 * @param imageBackground obrazok pozadia pre danu stranku
 * @param imageButton obrazok tlacidla zobrazujuceho sa na stranke
 */
data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageBackground: Int,
    @DrawableRes val imageButton: Int
)