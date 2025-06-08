package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.fitnessflowapp.R

//komentare ok

/**
 * enum trieda reprezentujuca typy obrazoviek v workoutracker
 * identifikuje sekcie/obrazovky
 */
enum class ScreenType {
    TRACKER, ALL_WORKOUTS, ADD_WORKOUT, ADD_EXERCISE, EXERCISE_INFO, SCHEDULE
}

/**
 * objekt, ktory poskytuje titulok pre kazdu obrazovku
 * na zaklade hodnoty ScreenType
 * @param screenType typ obrazovky pre ktoru sa ma vratit nadpis
 * @return text z strings.xml
 */
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