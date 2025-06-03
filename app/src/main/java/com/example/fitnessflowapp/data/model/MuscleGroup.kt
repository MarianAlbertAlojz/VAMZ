package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle_group")
data class MuscleGroup(
    @PrimaryKey(autoGenerate = true) val muscleGroupId: Int = 0,
    val description: String
)