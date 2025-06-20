package com.example.fitnessflowapp.static

import android.content.Context
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.data.model.OnboardingPage

//ok
/**
 * vracia zoznam onboarding stranok zobrazovanych pri prvom spusteni aplikacie
 * kazda stranka obsahuje nadpis, popis a obrazky pre pozadie a tlacidlo
 */
object OnboardingPages {

    /**
     * vrati zoznam onboarding stranok
     *
     * @param context nacitanie stringov zo suboru resources
     * @return obsah ktory sa zobrazi pocas uvitania
     */
    fun getPages(context: Context): List<OnboardingPage> = listOf(
        OnboardingPage(
            title = context.getString(R.string.onboarding_title_1),
            description = context.getString(R.string.onboarding_desc_1),
            imageBackground = R.drawable.onboarding_1,
            imageButton = R.drawable.onboarding_button_1
        ),
        OnboardingPage(
            title = context.getString(R.string.onboarding_title_2),
            description = context.getString(R.string.onboarding_desc_2),
            imageBackground = R.drawable.onboarding_2,
            imageButton = R.drawable.onboarding_button_2
        ),
        OnboardingPage(
            title = context.getString(R.string.onboarding_title_3),
            description = context.getString(R.string.onboarding_desc_3),
            imageBackground = R.drawable.onboarding_3,
            imageButton = R.drawable.onboarding_button_3
        ),
        OnboardingPage(
            title = context.getString(R.string.onboarding_title_4),
            description = context.getString(R.string.onboarding_desc_4),
            imageBackground = R.drawable.onboarding_4,
            imageButton = R.drawable.onboarding_button_4
        )
    )
}