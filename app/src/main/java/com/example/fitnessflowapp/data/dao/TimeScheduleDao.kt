package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessflowapp.data.model.TimeSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeScheduleDao {
    @Query("SELECT * FROM time_schedule")
    fun getAllSchedules(): Flow<List<TimeSchedule>>

    @Query("SELECT * FROM time_schedule WHERE timeScheduleId = :id")
    suspend fun getScheduleById(id: Int): TimeSchedule?

    @Query("SELECT * FROM time_schedule WHERE userId = :userId")
    fun getSchedulesForUser(userId: Int): Flow<List<TimeSchedule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: TimeSchedule): Long

    @Delete
    suspend fun deleteSchedule(schedule: TimeSchedule)
}