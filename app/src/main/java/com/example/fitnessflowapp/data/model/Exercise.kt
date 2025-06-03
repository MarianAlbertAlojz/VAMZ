package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["workoutId"],
            childColumns  = ["workoutId"],
            onDelete      = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workoutId")]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int = 0,
    val name: String,
    val sets: Int,
    val repetitions: Int,
    val weight: Float,             // kg/lbs?
    val difficulty: Difficulty,
    val notes: String?,
    val photoUri: String?, // tu musim prist na to ako to uklad tie fotky
    val workoutId: Int
)