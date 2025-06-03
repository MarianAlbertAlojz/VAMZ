package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int = 0,
    val name: String,
    val type: WorkoutType,
    val durationMin: Int,           //minuta
    val numberOfExercises: Int,
    val calories: Float,            //odhad
    val difficulty: Difficulty,
    val notes: String?
)