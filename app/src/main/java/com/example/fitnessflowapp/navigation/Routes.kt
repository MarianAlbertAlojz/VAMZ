package com.example.fitnessflowapp.navigation

//sealed class means a closed set of defined subclasses
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Onboarding : Screen("onboarding")
    object Setup : Screen("setup")
    object SetupGender : Screen("setup_gender")
    object SetupAge : Screen("setup_age")
    object SetupWeight : Screen("setup_weight")
    object SetupHeight : Screen("setup_height")
    object SetupGoal : Screen("setup_goal")
    object SetupPhysicalActivity : Screen("setup_activity")
    object SetupFillProfile : Screen("setup_profile")
    //later ill add other screens
    object Home : Screen("home")
    //object AllWorkouts : Screen("allworkouts")
    //object Home : Screen("home")
    //object Home : Screen("home")
}