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
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.ui.components.DetailRow
import com.example.fitnessflowapp.ui.components.ExerciseItem
import com.example.fitnessflowapp.ui.components.NameInputDialog
import com.example.fitnessflowapp.ui.components.NotesRow
import com.example.fitnessflowapp.ui.components.SelectionDialog
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader
import com.example.fitnessflowapp.ui.viewmodel.WorkoutCreationViewModel

//strings done
//dokumentacia
@Composable
fun AddWorkoutScreen(
    navController: NavHostController,
    workoutVm: WorkoutCreationViewModel = viewModel()
) {
    val title = ScreenTitleProvider.getTitle(ScreenType.ADD_WORKOUT)
    val context = LocalContext.current
    var isNotesExpanded by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogOptions by remember { mutableStateOf(listOf<String>()) }
    var dialogOnSave: (String) -> Unit by remember { mutableStateOf({}) }
    var showNameInputDialog by remember { mutableStateOf(false) }
    val workouts by workoutVm.allWorkouts.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            WorkoutDetailsHeader(
                title = title,
                onBackClick = { navController.popBackStack() },
                onOptionsClick = { /* menu */ },
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
                    Text(
                        text = stringResource(id = R.string.workout_details),
                        style = MaterialTheme.typography.labelMedium
                    )
                    DetailRow(
                        label = stringResource(id = R.string.details_difficulty),
                        value = workoutVm.workoutDifficulty,
                        onClick = {
                            dialogTitle = context.getString(R.string.select_difficulty)
                            dialogOptions = listOf(
                                context.getString(R.string.difficulty_easy),
                                context.getString(R.string.difficulty_medium),
                                context.getString(R.string.difficulty_hard)
                            )
                            dialogOnSave = { workoutVm.updateWorkoutDifficulty(it) }
                            showDialog = true
                        }
                    )
                    NotesRow(
                        isExpanded = isNotesExpanded,
                        onToggle = { isNotesExpanded = !isNotesExpanded },
                        text = workoutVm.workoutNotes,
                        onTextChange = { workoutVm.updateNotes(it) },
                        title = stringResource(id = R.string.notes)
                    )
                    DetailRow(
                        label = stringResource(id = R.string.workout_details_name),
                        value = workoutVm.workoutName.ifEmpty {
                            stringResource(id = R.string.enter_name)
                        },
                        onClick = {
                            dialogOnSave = { workoutVm.updateWorkoutName(it) }
                            showNameInputDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        workoutVm.workoutName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                itemsIndexed(workoutVm.exercises) { index, exercise ->
                    ExerciseItem(
                        name = exercise.exerciseName,
                        imageRes = R.drawable.icon_add,
                        onInfoClick = { navController.navigate(Screen.InfoExercise.route) }
                    )
                }

                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddExercise.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 88.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_add),
                contentDescription = stringResource(id = R.string.add)
            )
        }

        Button(
            onClick = {
                workoutVm.saveCurrentWorkout {
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.save))
        }

        if (showDialog) {
            SelectionDialog(
                title = dialogTitle,
                options = dialogOptions,
                currentSelection = dialogOptions.firstOrNull { it == workoutVm.workoutDifficulty } ?: "",
                onDismiss = { showDialog = false },
                onSave = {
                    dialogOnSave(it)
                    showDialog = false
                }
            )
        }

        if (showNameInputDialog) {
            NameInputDialog(
                currentName = workoutVm.workoutName,
                onDismiss = { showNameInputDialog = false },
                onSave = {
                    workoutVm.updateWorkoutName(it)
                    showNameInputDialog = false
                }
            )
        }
    }
}

