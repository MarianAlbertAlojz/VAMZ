package com.example.fitnessflowapp.ui.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.fitnessflow.ui.onboarding.OnboardingPageRepository
import com.example.fitnessflowapp.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavHostController
) {
    val pageCount = OnboardingPageRepository.getPages().size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false
    ) { page ->
        OnboardingItem(
            page = OnboardingPageRepository.getPages()[page],
            onNextClick = {
                if (page < pageCount - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(page + 1)
                    }
                } else {
                    navController.navigate(Screen.Setup.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = false }
                    }

                }
            }
        )
    }
}
