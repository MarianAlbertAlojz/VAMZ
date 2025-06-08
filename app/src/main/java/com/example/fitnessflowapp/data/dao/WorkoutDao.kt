package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessflowapp.data.model.Workout
import kotlinx.coroutines.flow.Flow


/**
 * DAO rozhranie pre databazove operacie nad tabulkou workout
 * definuje metody na nacitanie, vlozenie a mazanie workoutov
 *
 */
@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout")
    fun getAllWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workout WHERE workoutId = :id")
    suspend fun getWorkoutById(id: Int): Workout?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout): Long

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}
