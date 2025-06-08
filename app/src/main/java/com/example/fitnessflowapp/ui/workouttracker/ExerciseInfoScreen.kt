package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.DetailRow
import com.example.fitnessflowapp.ui.components.NameInputDialog
import com.example.fitnessflowapp.ui.components.NotesRow
import com.example.fitnessflowapp.ui.components.SelectionDialog
import com.example.fitnessflowapp.ui.components.SetRow
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader
import com.example.fitnessflowapp.ui.viewmodel.WorkoutCreationViewModel

//strings done
//komentare
@Composable
fun ExerciseInfoScreen(
    navController: NavHostController,
    workoutVm: WorkoutCreationViewModel = viewModel()
) {
    val context = LocalContext.current
    val selectedExercise = workoutVm.selectedExercise
    LaunchedEffect(selectedExercise) {
        selectedExercise?.let { exercise ->
            workoutVm.updateExerciseName(exercise.exerciseName)
            workoutVm.updateExerciseDifficulty(exercise.difficulty)
            workoutVm.updateMuscleGroup(exercise.targetMuscleGroup)
            workoutVm.updateExerciseType(exercise.exerciseType)
            workoutVm.updateExerciseNotes(exercise.notes)
            workoutVm.resetSets()
            repeat(exercise.sets) {
                workoutVm.addSet()
            }
            if (workoutVm.currentSets.isNotEmpty()) {
                workoutVm.updateSet(0, Triple(1, exercise.repetitions, exercise.weight.toInt()))
            }
        }
    }

    var isNotesExpanded by remember { mutableStateOf(false) }
    var showNameInputDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogOptions by remember { mutableStateOf(listOf<String>()) }
    var dialogOnSave: (String) -> Unit by remember { mutableStateOf({}) }
    val title = ScreenTitleProvider.getTitle(ScreenType.EXERCISE_INFO)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            WorkoutDetailsHeader(
                title = title,
                onBackClick = { navController.popBackStack() },
                onOptionsClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Text(stringResource(id = R.string.exercise_details), style = MaterialTheme.typography.labelMedium)
                }

                item {
                    DetailRow(
                        label = stringResource(id = R.string.details_difficulty),
                        value = workoutVm.exerciseDifficulty,
                        onClick = {
                            dialogTitle = context.getString(R.string.select_difficulty)
                            dialogOptions = listOf(
                                context.getString(R.string.difficulty_easy),
                                context.getString(R.string.difficulty_medium),
                                context.getString(R.string.difficulty_hard)
                            )
                            dialogOnSave = { workoutVm.updateExerciseDifficulty(it) }
                            showDialog = true
                        }
                    )
                }

                item {
                    NotesRow(
                        isExpanded = isNotesExpanded,
                        onToggle = { isNotesExpanded = !isNotesExpanded },
                        text = workoutVm.exerciseNotes,
                        onTextChange = { workoutVm.updateExerciseNotes(it) },
                        title = stringResource(id = R.string.notes)
                    )
                }

                item {
                    DetailRow(
                        label = stringResource(id = R.string.exercise_details_name),
                        value = workoutVm.exerciseName.ifEmpty { stringResource(id = R.string.enter_exercise_name) },
                        onClick = { showNameInputDialog = true }
                    )
                }

                item {
                    DetailRow(
                        label = stringResource(id = R.string.exercise_details_target),
                        value = workoutVm.muscleGroup.ifEmpty { stringResource(id = R.string.select_target_muscle) },
                        onClick = {
                            dialogTitle = context.getString(R.string.select_target_muscle)
                            dialogOptions = listOf(
                                context.getString(R.string.muscle_legs),
                                context.getString(R.string.muscle_chest),
                                context.getString(R.string.muscle_back),
                                context.getString(R.string.muscle_arms),
                                context.getString(R.string.muscle_shoulders)
                            )
                            dialogOnSave = { workoutVm.updateMuscleGroup(it) }
                            showDialog = true
                        }
                    )
                }

                item {
                    DetailRow(
                        label = stringResource(id = R.string.exercise_details_type),
                        value = workoutVm.exerciseType.ifEmpty { stringResource(id = R.string.select_exercise_type) },
                        onClick = {
                            dialogTitle = context.getString(R.string.select_exercise_type)
                            dialogOptions = listOf(
                                context.getString(R.string.type_strength),
                                context.getString(R.string.type_flexibility),
                                context.getString(R.string.type_cardio),
                                context.getString(R.string.type_mobility)
                            )
                            dialogOnSave = { workoutVm.updateExerciseType(it) }
                            showDialog = true
                        }
                    )
                }

                item {
                    NotesRow(
                        isExpanded = isNotesExpanded,
                        onToggle = { isNotesExpanded = !isNotesExpanded },
                        text = workoutVm.exerciseNotes,
                        onTextChange = { workoutVm.updateExerciseNotes(it) },
                        title = stringResource(id = R.string.exercise_info_details)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        stringResource(id = R.string.custom_sets_title),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                itemsIndexed(workoutVm.currentSets) { index, triple ->
                    SetRow(
                        index = index,
                        set = triple.first,
                        reps = triple.second,
                        weight = triple.third,
                        onRemove = { workoutVm.removeSet(index) },
                        onChange = { newSet -> workoutVm.updateSet(index, newSet) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        FloatingActionButton(
            onClick = { workoutVm.addSet() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 88.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add))
        }

        Button(
            onClick = {
                val updated = selectedExercise?.copy(
                    exerciseName = workoutVm.exerciseName,
                    targetMuscleGroup = workoutVm.muscleGroup,
                    exerciseType = workoutVm.exerciseType,
                    difficulty = workoutVm.exerciseDifficulty,
                    notes = workoutVm.exerciseNotes,
                    sets = workoutVm.currentSets.size,
                    repetitions = workoutVm.currentSets.firstOrNull()?.second ?: 0,
                    weight = workoutVm.currentSets.firstOrNull()?.third?.toFloat() ?: 0f,
                    setsData = workoutVm.currentSets.toList()
                )
                if (updated != null) {
                    val index = workoutVm.exercises.indexOfFirst { it == selectedExercise }
                    if (index != -1) {
                        workoutVm.exercises[index] = updated
                        workoutVm.updateSelectedExercise(updated)
                    }
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(stringResource(id = R.string.save))
        }

        if (showDialog) {
            SelectionDialog(
                title = dialogTitle,
                options = dialogOptions,
                currentSelection = dialogOptions.firstOrNull {
                    it == workoutVm.exerciseDifficulty ||
                            it == workoutVm.exerciseType ||
                            it == workoutVm.muscleGroup
                } ?: "",
                onDismiss = { showDialog = false },
                onSave = {
                    dialogOnSave(it)
                    showDialog = false
                }
            )
        }

        if (showNameInputDialog) {
            NameInputDialog(
                currentName = workoutVm.exerciseName,
                onDismiss = { showNameInputDialog = false },
                onSave = {
                    workoutVm.updateExerciseName(it)
                    showNameInputDialog = false
                }
            )
        }
    }
}


