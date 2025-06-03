package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "muscle_group_exercise",
    foreignKeys = [
        ForeignKey(MuscleGroup::class, ["muscleGroupId"], ["muscleGroupId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(Exercise::class,    ["exerciseId"],    ["exerciseId"],    onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("muscleGroupId"), Index("exerciseId")]
)
data class MuscleGroupExercise(
    @PrimaryKey(autoGenerate = true) val mgExerciseId: Int = 0,
    val muscleGroupId: Int,
    val exerciseId: Int,
    val targetLevel: String?
)
