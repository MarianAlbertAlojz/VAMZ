package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "muscle_group_workout",
    foreignKeys = [
        ForeignKey(MuscleGroup::class, ["muscleGroupId"], ["muscleGroupId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(Workout::class,     ["workoutId"],    ["workoutId"],    onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("muscleGroupId"), Index("workoutId")]
)
data class MuscleGroupWorkout(
    @PrimaryKey(autoGenerate = true) val mgWorkoutId: Int = 0,
    val muscleGroupId: Int,
    val workoutId: Int,
    val focusLevel: String?        //domysliet co tu bude
)
