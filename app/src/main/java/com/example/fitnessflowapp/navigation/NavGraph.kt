package com.example.fitnessflowapp.navigation

import ProfileSetupRoute
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessflowapp.data.repository.SetupPageRepository
import com.example.fitnessflowapp.ui.onboarding.OnboardingScreen
import com.example.fitnessflowapp.ui.onboarding.WelcomeScreen
import com.example.fitnessflowapp.ui.setup.AgeScreen
import com.example.fitnessflowapp.ui.setup.GenderScreen
import com.example.fitnessflowapp.ui.setup.GoalScreen
import com.example.fitnessflowapp.ui.setup.HeightScreen
import com.example.fitnessflowapp.ui.setup.PhysicalActivityLevelScreen
import com.example.fitnessflowapp.ui.setup.SetupScreen
import com.example.fitnessflowapp.ui.setup.WeightScreen

enum class SetupStep(val route: String, val pageIndex: Int) {
    Setup("setup", 0),
    Gender("setup_gender", 1),
    Age("setup_age", 2),
    Weight("setup_weight", 3),
    Height("setup_height", 4),
    Goal("setup_goal", 5),
    Activity("setup_activity", 6),
    Profile("setup_profile", 7)
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate(Screen.Setup.route) //nastavene kvoli debugu potom prehodit
                    //navController.navigate(Screen.Onboarding.route)
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }

        composable(Screen.Setup.route) {
            SetupScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Setup.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Setup.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupGender.route) }
            )
        }

        composable(Screen.SetupGender.route) {
            GenderScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Gender.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Gender.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupAge.route) },
            )
        }

        composable(Screen.SetupAge.route) {
            AgeScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Age.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Age.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupWeight.route) },
            )
        }

        composable(Screen.SetupWeight.route) {
            WeightScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Weight.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Weight.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupHeight.route) },
            )
        }

        composable(Screen.SetupHeight.route) {
            HeightScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Height.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Height.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupGoal.route) },
            )
        }

        composable(Screen.SetupGoal.route) {
            GoalScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Goal.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Goal.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupPhysicalActivity.route) },
            )
        }

        composable(Screen.SetupPhysicalActivity.route) {
            PhysicalActivityLevelScreen(
                title = SetupPageRepository.getPages(context)[SetupStep.Activity.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Activity.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupFillProfile.route) },
            )
        }

        composable(Screen.SetupFillProfile.route) {
            ProfileSetupRoute(navController)//toto si este prejst a dopisat
        }

        //podla chuti potom dalsia navigacia

    }
}