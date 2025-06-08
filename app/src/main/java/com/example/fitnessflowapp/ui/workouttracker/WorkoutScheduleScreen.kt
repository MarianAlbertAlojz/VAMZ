package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader

//strings done
//komentare
@Composable
fun WorkoutScheduleScreen(navController: NavHostController) {
    val context = LocalContext.current
    val title = ScreenTitleProvider.getTitle(ScreenType.SCHEDULE)
    var selectedDate by remember { mutableStateOf(context.getString(R.string.date_example)) }
    var showAddDialog by remember { mutableStateOf(false) }
    val workouts = remember {
        mutableStateListOf(
            WorkoutItem(context.getString(R.string.ab_workout_title), context.getString(R.string.time_7_30_am)),
            WorkoutItem(context.getString(R.string.upperbody_workout_title), context.getString(R.string.time_9_00_am)),
            WorkoutItem(context.getString(R.string.lowerbody_workout_title), context.getString(R.string.time_3_00_pm))
        )
    }


    Column(modifier = Modifier.fillMaxSize()) {
        WorkoutDetailsHeader(
            title = title,
            onBackClick = { navController.popBackStack() },
            onOptionsClick = { }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = selectedDate, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(workouts) { workout ->
                        WorkoutCard(workout)
                    }
                }
            }

            FloatingActionButton(
                onClick = { showAddDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add))
            }

            if (showAddDialog) {
                AddWorkoutDialog(
                    onDismiss = { showAddDialog = false },
                    onAdd = {
                        workouts.add(it)
                        showAddDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: WorkoutItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(workout.name, fontWeight = FontWeight.Bold)
                Text(workout.time, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}


@Composable
fun AddWorkoutDialog(onDismiss: () -> Unit, onAdd: (WorkoutItem) -> Unit) {
    var name by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotBlank() && time.isNotBlank()) {
                    onAdd(WorkoutItem(name, time))
                }
            }) {
                Text(text = stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = { Text(stringResource(id = R.string.add_schedule_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(id = R.string.workout_details_name)) }
                )
                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text(stringResource(id = R.string.time)) }
                )
            }
        }
    )
}

data class WorkoutItem(val name: String, val time: String)
