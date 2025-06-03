package com.example.fitnessflowapp.data.dao

import androidx.room.*
import com.example.fitnessflowapp.data.model.FinishedWorkout
import kotlinx.coroutines.flow.Flow

@Dao
interface FinishedWorkoutDao {
    @Query("SELECT * FROM finished_workout")
    fun getAllFinished(): Flow<List<FinishedWorkout>>

    @Query("SELECT * FROM finished_workout WHERE finishedWorkoutId = :id")
    suspend fun getFinishedById(id: Int): FinishedWorkout?

    @Query("SELECT * FROM finished_workout WHERE userId = :userId")
    fun getFinishedForUser(userId: Int): Flow<List<FinishedWorkout>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFinished(fw: FinishedWorkout): Long

    @Delete
    suspend fun deleteFinished(fw: FinishedWorkout)
}