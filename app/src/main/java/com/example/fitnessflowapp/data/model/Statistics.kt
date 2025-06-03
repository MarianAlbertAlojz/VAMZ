package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "statistics",
    foreignKeys = [
        ForeignKey(UserProfile::class, ["id"], ["userId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("userId")]
)
data class Statistics(
    @PrimaryKey(autoGenerate = true) val statsId: Int = 0,
    val date: Date,
    val userId: Int,
    val totalCaloriesBurned: Int
)
