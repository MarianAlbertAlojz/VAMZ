package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * entita reprezentujuca workout v databaze
 * kazdy workout obsahuje nazov, typ, trvanie, pocet cvikov, narocnost, odhad spaleneho kalorickeho vydaja a volitelnu poznamku
 *
 * enum hodnoty ako WorkoutType a Difficulty su konvertovane cez TypeConverter
 *
 * @Entity definuje databazovu tabulku s nazvom "workout"
 */
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