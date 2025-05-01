package com.example.fitnessflowapp.ui.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnessflow.ui.onboarding.onboardingPages

@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        OnboardingItem(page = onboardingPages[page])
    }
}
