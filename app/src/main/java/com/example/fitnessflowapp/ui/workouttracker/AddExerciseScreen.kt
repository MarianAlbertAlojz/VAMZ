package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader
import com.example.fitnessflowapp.ui.viewmodel.WorkoutCreationViewModel


//strings done
//komentare
@Composable
fun AddExerciseScreen(
    navController: NavHostController,
    workoutVm: WorkoutCreationViewModel = viewModel()
) {
    val context = LocalContext.current
    var isNotesExpanded by remember { mutableStateOf(false) }
    var showNameInputDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogOptions by remember { mutableStateOf(listOf<String>()) }
    var dialogOnSave: (String) -> Unit by remember { mutableStateOf({}) }
    val title = ScreenTitleProvider.getTitle(ScreenType.ADD_EXERCISE)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        WorkoutDetailsHeader(
            title = title,
            onBackClick = { navController.popBackStack() },
            onOptionsClick = { /* menu */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                stringResource(R.string.exercise_details),
                style = MaterialTheme.typography.labelMedium
            )

            DetailRow(
                label = stringResource(R.string.details_difficulty),
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

            NotesRow(
                isExpanded = isNotesExpanded,
                onToggle = { isNotesExpanded = !isNotesExpanded },
                text = workoutVm.exerciseNotes,
                onTextChange = { workoutVm.updateExerciseNotes(it) },
                title = context.getString(R.string.notes)
            )

            DetailRow(
                label = context.getString(R.string.exercise_details_name),
                value = workoutVm.exerciseName.ifEmpty { context.getString(R.string.enter_exercise_name) },
                onClick = { showNameInputDialog = true }
            )

            DetailRow(
                label = context.getString(R.string.exercise_details_target),
                value = workoutVm.muscleGroup.ifEmpty { context.getString(R.string.select_target_muscle) },
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

            DetailRow(
                label = context.getString(R.string.exercise_details_type),
                value = workoutVm.exerciseType.ifEmpty { context.getString(R.string.select_exercise_type) },
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

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    workoutVm.addExercise(workoutVm.toExerciseModel())
                    navController.popBackStack()
                    workoutVm.resetExerciseForm()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(context.getString(R.string.add))
            }
        }
        if (showDialog) {
            SelectionDialog(
                title = dialogTitle,
                options = dialogOptions,
                currentSelection = dialogOptions.firstOrNull { it == workoutVm.exerciseDifficulty || it == workoutVm.exerciseType || it == workoutVm.muscleGroup }
                    ?: "",
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
