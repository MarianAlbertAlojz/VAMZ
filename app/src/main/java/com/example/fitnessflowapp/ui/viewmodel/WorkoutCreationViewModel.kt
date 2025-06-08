package com.example.fitnessflowapp.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.data.model.Difficulty
import com.example.fitnessflowapp.data.model.Exercise
import com.example.fitnessflowapp.data.model.Workout
import com.example.fitnessflowapp.data.model.WorkoutType
import com.example.fitnessflowapp.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//strings done
//komentare
class WorkoutCreationViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    private val repository = WorkoutRepository.getInstance(app)

    val allWorkouts: StateFlow<List<Workout>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    var workoutName by mutableStateOf(app.getString(R.string.default_workout_name))
        private set

    var workoutDifficulty by mutableStateOf(app.getString(R.string.difficulty_hard))
        private set

    var workoutNotes by mutableStateOf("")
        private set

    var exerciseName by mutableStateOf(app.getString(R.string.default_exercise_name))
        private set

    var exerciseDifficulty by mutableStateOf(app.getString(R.string.difficulty_hard))
        private set

    var muscleGroup by mutableStateOf("")
        private set

    var exerciseType by mutableStateOf("")
        private set

    var exerciseNotes by mutableStateOf("")
        private set

    var workoutId by mutableStateOf(0)
        private set

    var currentSets = mutableStateListOf<Triple<Int, Int, Int>>()
        private set

    val exercises = mutableStateListOf<ExerciseModel>()

    var selectedExercise by mutableStateOf<ExerciseModel?>(null)
        private set


    //funckie exercise
    fun updateExerciseName(name: String) {
        exerciseName = name
    }

    fun updateExerciseDifficulty(level: String) {
        exerciseDifficulty = level
    }

    fun updateMuscleGroup(group: String) {
        muscleGroup = group
    }

    fun updateExerciseType(type: String) {
        exerciseType = type
    }

    fun updateExerciseNotes(newNotes: String) {
        exerciseNotes = newNotes
    }

    fun updateWorkoutId(id: Int) {
        workoutId = id
    }

    fun addSet() {
        currentSets.add(Triple(currentSets.size + 1, 10, 0))
    }

    fun updateSet(index: Int, newSet: Triple<Int, Int, Int>) {
        if (index in currentSets.indices) {
            currentSets[index] = newSet
        }
    }

    fun removeSet(index: Int) {
        if (index in currentSets.indices) {
            currentSets.removeAt(index)
        }
    }

    fun resetSets() {
        currentSets.clear()
    }

    fun toExerciseModel(): ExerciseModel {
        val mainSet = currentSets.firstOrNull() ?: Triple(1, 0, 0)
        return ExerciseModel(
            exerciseName = exerciseName,
            targetMuscleGroup = muscleGroup,
            exerciseType = exerciseType,
            difficulty = exerciseDifficulty,
            notes = exerciseNotes,
            sets = currentSets.size,
            repetitions = mainSet.second,
            weight = mainSet.third.toFloat(),
            photoUri = "",
            workoutId = workoutId,
            setsData = currentSets.toList()
        )
    }

    fun toExercise(): Exercise {
        return Exercise(
            exerciseName = exerciseName,
            targetMuscleGroup = muscleGroup,
            exerciseType = exerciseType,
            difficulty = exerciseDifficulty,
            notes = exerciseNotes,
            sets = 0,
            repetitions = 0,
            weight = 0.00f,
            photoUri = "",
            workoutId = workoutId
        )
    }

    fun addExercise(exercise: ExerciseModel) {
        exercises.add(exercise)
    }

    fun removeExercise(index: Int) {
        if (index in exercises.indices) {
            exercises.removeAt(index)
        }
    }

    fun updateSelectedExercise(newExercise: ExerciseModel) {
        selectedExercise = newExercise
        resetSets()
        currentSets.addAll(newExercise.setsData)
    }

    fun clearSelectedExercise() {
        selectedExercise = null
    }

    fun resetExerciseForm() {
        exerciseName = ""
        muscleGroup = ""
        exerciseType = ""
        exerciseDifficulty = ""
        exerciseNotes = ""
        resetSets()
    }

    //funckie workout

    fun updateWorkoutName(name: String) {
        workoutName = name
    }

    fun updateWorkoutDifficulty(level: String) {
        workoutDifficulty = level
    }

    fun updateNotes(newNotes: String) {
        workoutNotes = newNotes
    }

    fun resetWorkout() {
        workoutName = ""
        workoutDifficulty = ""
        workoutNotes = ""
        exercises.clear()
    }

    fun saveCurrentWorkout(onSaved: () -> Unit) {
        viewModelScope.launch {
            val workout = Workout(
                name = workoutName,
                type = WorkoutType.STRENGTH,
                durationMin = 30,
                numberOfExercises = exercises.size,
                calories = 250f,
                difficulty = Difficulty.valueOf(workoutDifficulty.uppercase()),
                notes = workoutNotes
            )
            // repository.saveWorkoutWithExercises(workout, exercises)
            onSaved()
        }
    }

    fun saveWorkoutAsync(workout: Workout) {
        viewModelScope.launch {
            // repository.saveWorkoutWithExercises(workout, exercises)
        }
    }
}
