package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "finished_workout",
    foreignKeys = [
        ForeignKey(Workout::class,     ["workoutId"], ["workoutId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(UserProfile::class, ["userId"],    ["id"],        onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("workoutId"), Index("userId")]
)
data class FinishedWorkout(
    @PrimaryKey(autoGenerate = true) val finishedWorkoutId: Int = 0,
    val workoutId: Int,
    val timeCompleted: Date,
    val status: ScheduleStatus,
    val userId: Int,
    val rating: Float?             // napr. 0.0–5.0
)