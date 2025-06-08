package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * entita reprezentujuca jeden cvik patriaci ku konkretnemu workoutu
 * kazdy cvik obsahuje udaje ako nazov pocet serii, opakovani, hmotnost atd.
 *
 * tabulka je prepojena cez cudzi kluc na workout tabulku – kazdy cvik patri prave k jednemu workoutu
 *
 *
 */
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
    val exerciseName: String,
    val targetMuscleGroup: String,
    val exerciseType: String,
    val sets: Int,
    val repetitions: Int,
    val weight: Float,             // kg/lbs?
    val difficulty: String,//Difficulty,
    val notes: String?,
    val photoUri: String?, // tu musim prist na to ako to uklad tie fotky
    val workoutId: Int

)