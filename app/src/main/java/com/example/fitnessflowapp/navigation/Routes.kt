package com.example.fitnessflowapp.navigation

/**
 * sealed class reprezentujuca vsetky obrazovky v aplikacii
 * kazda obrazovka ma svoju vlastnu route hodnotu, ktoru pouziva navigacia
 * pomahal mi chat gpt
 * pouzil pretoze tym zabezpecim, ze vsetky obrazovky
 * aplikacii su definovane na jednom mieste a vyhnem sa preklepom
 */
sealed class Screen(val route: String) {
    //Onboarding
    object Welcome : Screen("welcome")
    object Onboarding : Screen("onboarding")
    //Setup
    object Setup : Screen("setup")
    object SetupGender : Screen("setup_gender")
    object SetupAge : Screen("setup_age")
    object SetupWeight : Screen("setup_weight")
    object SetupHeight : Screen("setup_height")
    object SetupGoal : Screen("setup_goal")
    object SetupPhysicalActivity : Screen("setup_activity")
    object SetupFillProfile : Screen("setup_profile")
    //Main App Navigation
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Statistic : Screen("statistics")
    object ProgressPhoto : Screen("progress_photo")
    //Workout Tracker
    object WorkoutTrackerHome : Screen("workout_tracker_home")
    object AllWorkouts : Screen("workout_tracker_workouts")
    object AddWorkout : Screen("workout_add_workout")
    object AddExercise : Screen("workout_add_exercise")
    object InfoExercise : Screen("workout_info_exercise")
    object WorkoutSchedule : Screen("workout_schedule")
//object AllWorkouts : Screen("allworkouts")
    //object Home : Screen("home")
    //object Home : Screen("home")
}