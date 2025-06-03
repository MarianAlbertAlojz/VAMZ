package com.example.fitnessflowapp.data.model

import androidx.room.*
import java.util.*

@Entity(
    tableName = "time_schedule",
    foreignKeys = [
        ForeignKey(Workout::class,         ["workoutId"], ["workoutId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(UserProfile::class,     ["userId"],    ["id"],        onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("workoutId"), Index("userId")]
)
data class TimeSchedule(
    @PrimaryKey(autoGenerate = true) val timeScheduleId: Int = 0,
    val name: String,
    val dateTime: Date,
    val status: ScheduleStatus,
    val workoutId: Int,
    val userId: Int
)
