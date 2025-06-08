package com.example.fitnessflowapp.navigation

import ProfileScreen
import ProfileSetupRoute
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessflowapp.static.SetupPages
import com.example.fitnessflowapp.ui.navigation.HomeScreen
import com.example.fitnessflowapp.ui.navigation.ProgressPhotoScreen
import com.example.fitnessflowapp.ui.navigation.StatisticsScreen
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
import com.example.fitnessflowapp.ui.viewmodel.WorkoutCreationViewModel
import com.example.fitnessflowapp.ui.workouttracker.AddExerciseScreen
import com.example.fitnessflowapp.ui.workouttracker.AddWorkoutScreen
import com.example.fitnessflowapp.ui.workouttracker.AllWorkoutsScreen
import com.example.fitnessflowapp.ui.workouttracker.ExerciseInfoScreen
import com.example.fitnessflowapp.ui.workouttracker.WorkoutScheduleScreen
import com.example.fitnessflowapp.ui.workouttracker.WorkoutTrackerScreen

/**
 * hlavna navigacna funkcia aplikacie, ktora definuje vsetky dostupne obrazovky pomocou NavHost
*  https://developer.android.com/codelabs/jetpack-compose-navigation#8
 * kazdy composable blok reprezentuje jednu obrazovku (route) v aplikacii
 * podla route sa zobrazi prislusna obrazovka a zabezpeci sa logika pre navigaciu medzi nimi
 *
 * @param navController sluzi na ovladanie navigacie medzi obrazovkami
 * @param startDestination urcuje, ktora obrazovka sa ma zobrazit ako prva
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val context = LocalContext.current
    val setupVm: SetupViewModel = viewModel()
    val workoutVm : WorkoutCreationViewModel = viewModel()
    val uiSetupState by setupVm.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClick = {
                    //navController.navigate(Screen.Setup.route) //nastavene kvoli debugu potom prehodit
                    navController.navigate(Screen.Onboarding.route)
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }

        composable(Screen.Setup.route) {
            SetupScreen(
                title = SetupPages.getPages(context)[SetupStep.Setup.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Setup.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupGender.route) }//Screen.SetupFillProfile.route
            )
        }

        composable(Screen.SetupGender.route) {
            GenderScreen(
                gender = uiSetupState.gender,
                onGenderSelected = setupVm::updateGender,
                title = SetupPages.getPages(context)[SetupStep.Gender.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Gender.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupAge.route) }
            )
        }

        composable(Screen.SetupAge.route) {
            AgeScreen(
                age = uiSetupState.age,
                onAgeChanged = setupVm::updateAge,
                title = SetupPages.getPages(context)[SetupStep.Age.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Age.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupWeight.route) }
            )
        }

        composable(Screen.SetupWeight.route) {
            WeightScreen(
                weight = uiSetupState.weight,
                onWeightChanged = setupVm::updateWeight,
                title = SetupPages.getPages(context)[SetupStep.Weight.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Weight.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupHeight.route) }
            )
        }

        composable(Screen.SetupHeight.route) {
            HeightScreen(
                height = uiSetupState.height,
                onHeightChanged = setupVm::updateHeight,
                title = SetupPages.getPages(context)[SetupStep.Height.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Height.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupGoal.route) }
            )
        }

        composable(Screen.SetupGoal.route) {
            GoalScreen(
                selectedGoal = uiSetupState.goal,
                onGoalSelected = setupVm::updateGoal,
                title = SetupPages.getPages(context)[SetupStep.Goal.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Goal.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupPhysicalActivity.route) }
            )
        }

        composable(Screen.SetupPhysicalActivity.route) {
            PhysicalActivityLevelScreen(
                selectedLevel = uiSetupState.activityLevel,
                onLevelSelected = setupVm::updateActivityLevel,
                title = SetupPages.getPages(context)[SetupStep.Activity.pageIndex].title,
                description = SetupPages.getPages(context)[SetupStep.Activity.pageIndex].description,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.SetupFillProfile.route) }
            )
        }

        composable(Screen.SetupFillProfile.route) {
            ProfileSetupRoute(
                navController = navController,
                vm = setupVm
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.ProgressPhoto.route) {
            ProgressPhotoScreen(navController)
        }

        composable(Screen.Statistic.route) {
            StatisticsScreen(navController)
        }

        composable(Screen.WorkoutTrackerHome.route) {
            WorkoutTrackerScreen(
                onActionClick = { section ->
                    when (section) {
                        "workout_tracker_workouts" -> navController.navigate(Screen.AllWorkouts.route)
                        "schedule" -> navController.navigate(Screen.WorkoutSchedule.route)
                        "workout_add_workout" -> navController.navigate(Screen.AddWorkout.route)
                    }
                },
                onWorkoutClick = { },
                navController
            )
        }

        composable(Screen.AllWorkouts.route) {
            AllWorkoutsScreen(
                onWorkoutClick = { },
                navController
            )
        }

        composable(Screen.AddWorkout.route) {
            AddWorkoutScreen(navController, workoutVm = workoutVm)
        }


        composable(Screen.AddExercise.route) {
            AddExerciseScreen(navController, workoutVm = workoutVm)
        }

        composable(Screen.InfoExercise.route) {
            ExerciseInfoScreen(navController, workoutVm = workoutVm)
        }

        composable(Screen.WorkoutSchedule.route) {
            WorkoutScheduleScreen(navController)
        }


        //podla chuti potom dalsia navigacia

    }

}