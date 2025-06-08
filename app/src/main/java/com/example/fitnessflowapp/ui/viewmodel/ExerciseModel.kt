package com.example.fitnessflowapp.ui.viewmodel

//komentare

data class ExerciseModel(
    val exerciseName: String,
    val targetMuscleGroup: String,
    val exerciseType: String,
    val difficulty: String,
    val notes: String,
    val sets: Int,
    val repetitions: Int,
    val weight: Float,
    val photoUri: String,
    val workoutId: Int,
    val setsData: List<Triple<Int, Int, Int>> = emptyList()
)
