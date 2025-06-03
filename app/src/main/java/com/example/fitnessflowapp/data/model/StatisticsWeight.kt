package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "statistics_weight",
    foreignKeys = [
        ForeignKey(Statistics::class, ["statsId"], ["statsId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("statsId")]
)
data class StatisticsWeight(
    @PrimaryKey(autoGenerate = true) val statWeightId: Int = 0,
    val statsId: Int,
    val weight: Float,
    val goalWeight: Float?,
    val recordedAt: Date
)