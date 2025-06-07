package com.example.fitnessflowapp.static

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.fitnessflowapp.R

enum class ScreenType {
    TRACKER, ALL_WORKOUTS, ADD_WORKOUT, ADD_EXERCISE, EXERCISE_INFO, SCHEDULE
}

object ScreenTitleProvider {
    @Composable
    fun getTitle(screenType: ScreenType): String {
        return when (screenType) {
            ScreenType.TRACKER -> stringResource(R.string.workout_tracker_home_title)
            ScreenType.ADD_WORKOUT -> stringResource(R.string.add_your_workout_title)
            ScreenType.ALL_WORKOUTS -> stringResource(R.string.all_workouts_title)
            ScreenType.ADD_EXERCISE -> stringResource(R.string.add_your_exercise_title)
            ScreenType.EXERCISE_INFO -> stringResource(R.string.exercise_info_title)
            ScreenType.SCHEDULE -> stringResource(R.string.workout_schedule_title)
        }
    }
}