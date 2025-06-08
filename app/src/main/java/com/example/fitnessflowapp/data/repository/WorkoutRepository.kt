package com.example.fitnessflowapp.data.repository

import AppDatabase
import android.content.Context
import com.example.fitnessflowapp.data.dao.ExerciseDao
import com.example.fitnessflowapp.data.dao.WorkoutDao
import com.example.fitnessflowapp.data.model.Exercise
import com.example.fitnessflowapp.data.model.Workout
import kotlinx.coroutines.flow.Flow

/**
 * pomahal mi chat gpt
 * repository trieda, ktora zabezpecuje pristup k datam pre workouty a cviky
 * obsahuje metody na ukladanie a nacitanie treningov a ich cvikov
 *
 * implementuje singleton vzor pomocou companion object
 * instanciu je mozne ziskat cez funkciu getInstance(context)
 *
 * @param workoutDao DAO pre operacie s workout entitami
 * @param exerciseDao DAO pre operacie s exercise entitami
 */
class WorkoutRepository private constructor(
    private val workoutDao: WorkoutDao,
    private val exerciseDao: ExerciseDao
) {

    suspend fun saveWorkoutWithExercises(workout: Workout, exercises: List<Exercise>) {
        val workoutId = workoutDao.insertWorkout(workout).toInt()

        val exercisesWithWorkoutId = exercises.map {
            it.copy(workoutId = workoutId)
        }

        exerciseDao.insertExercises(exercisesWithWorkoutId)
    }

    fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    fun getExercisesForWorkout(workoutId: Int): Flow<List<Exercise>> =
        exerciseDao.getExercisesForWorkout(workoutId)

    companion object {
        @Volatile private var INSTANCE: WorkoutRepository? = null

        /**
         * vrati existujucu alebo vytvori novu instanciu WorkoutRepository
         *
         * @param context ziskanie pristupu k databaze
         * @return singleton instancia
         */
        fun getInstance(context: Context): WorkoutRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WorkoutRepository(
                    AppDatabase.getInstance(context).workoutDao(),
                    AppDatabase.getInstance(context).exerciseDao()
                ).also { INSTANCE = it }
            }
    }
}
