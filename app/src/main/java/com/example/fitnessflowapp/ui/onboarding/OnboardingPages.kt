package com.example.fitnessflow.ui.onboarding


import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.onboarding.OnboardingPage

val onboardingPages = listOf(
    OnboardingPage(
        title = "Track Your Goal",
        description = "Don't worry if you have trouble determining your goals. We can help you set them!",
        imageBackground = R.drawable.onboarding_1,
        imageButton = R.drawable.onboarding_button_1
    ),
    OnboardingPage(
        title = "Get Burn",
        description = "Let’s keep burning to achieve your goals. It hurts only temporarily, if you give up now, it will be permanent.",
        imageBackground = R.drawable.onboarding_2,
        imageButton = R.drawable.onboarding_button_2
    ),
    OnboardingPage(
        title = "Eat Well",
        description = "Let's start a healthy lifestyle with us, we can determine your diet every day. Healthy eating is fun!",
        imageBackground = R.drawable.onboarding_3,
        imageButton = R.drawable.onboarding_button_3
    ),
    OnboardingPage(
        title = "Improve Sleep Quality",
        description = "Improve the quality of your sleep with us, good sleep quality can bring a good mood in the morning.",
        imageBackground = R.drawable.onboarding_4,
        imageButton = R.drawable.onboarding_button_4
    )
)
