package com.example.fitnessflowapp.navigation

import ProfileSetupRoute
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessflowapp.data.repository.SetupPageRepository
import com.example.fitnessflowapp.ui.navigation.HomeScreen
import com.example.fitnessflowapp.ui.onboarding.OnboardingScreen
import com.example.fitnessflowapp.ui.onboarding.WelcomeScreen
import com.example.fitnessflowapp.ui.setup.AgeScreen
import com.example.fitnessflowapp.ui.setup.GenderScreen
import com.example.fitnessflowapp.ui.setup.GoalScreen
import com.example.fitnessflowapp.ui.setup.HeightScreen
import com.example.fitnessflowapp.ui.setup.PhysicalActivityLevelScreen
import com.example.fitnessflowapp.ui.setup.SetupScreen
import com.example.fitnessflowapp.ui.setup.WeightScreen
import com.example.fitnessflowapp.ui.viewmodel.SetupViewModel

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
    val vm: SetupViewModel = viewModel()
    val uiSetupState by vm.uiState.collectAsState()

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
                onNext = { navController.navigate(Screen.SetupGender.route) }//Screen.SetupFillProfile.route
            )
        }

        composable(Screen.SetupGender.route) {
            GenderScreen(
                gender = uiSetupState.gender,
                onGenderSelected = vm::updateGender,
                title = SetupPageRepository.getPages(context)[SetupStep.Gender.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Gender.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupAge.route) }
            )
        }

        composable(Screen.SetupAge.route) {
            AgeScreen(
                age = uiSetupState.age,
                onAgeChanged = vm::updateAge,
                title = SetupPageRepository.getPages(context)[SetupStep.Age.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Age.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupWeight.route) }
            )
        }

        composable(Screen.SetupWeight.route) {
            WeightScreen(
                weight = uiSetupState.weight,
                onWeightChanged = vm::updateWeight,
                title = SetupPageRepository.getPages(context)[SetupStep.Weight.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Weight.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupHeight.route) }
            )
        }

        composable(Screen.SetupHeight.route) {
            HeightScreen(
                height = uiSetupState.height,
                onHeightChanged = vm::updateHeight,
                title = SetupPageRepository.getPages(context)[SetupStep.Height.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Height.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupGoal.route) }
            )
        }

        composable(Screen.SetupGoal.route) {
            GoalScreen(
                selectedGoal = uiSetupState.goal,
                onGoalSelected = vm::updateGoal,
                title = SetupPageRepository.getPages(context)[SetupStep.Goal.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Goal.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupPhysicalActivity.route) }
            )
        }

        composable(Screen.SetupPhysicalActivity.route) {
            PhysicalActivityLevelScreen(
                selectedLevel = uiSetupState.activityLevel,
                onLevelSelected = vm::updateActivityLevel,
                title = SetupPageRepository.getPages(context)[SetupStep.Activity.pageIndex].title,
                description = SetupPageRepository.getPages(context)[SetupStep.Activity.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupFillProfile.route) }
            )
        }

        composable(Screen.SetupFillProfile.route) { backStackEntry ->
            ProfileSetupRoute(
                navController = navController,
                vm = vm
            )
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }

        //podla chuti potom dalsia navigacia

    }

}