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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessflowapp.static.ScreenTitleProvider
import com.example.fitnessflowapp.static.ScreenType
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader

@Composable
fun WorkoutScheduleScreen() {
    var selectedDate by remember { mutableStateOf("Fri, 14 May") }
    var showAddDialog by remember { mutableStateOf(false) }
    val workouts = remember {
        mutableStateListOf(
            WorkoutItem("Ab Workout", "7:30 AM"),
            WorkoutItem("Upperbody Workout", "9:00 AM"),
            WorkoutItem("Lowerbody Workout", "3:00 PM")
        )
    }
    val title = ScreenTitleProvider.getTitle(ScreenType.SCHEDULE)

    Column(modifier = Modifier.fillMaxSize()) {
        WorkoutDetailsHeader(
            title = title,
            onBackClick = { /* back */ },
            onOptionsClick = { /* menu? */ }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Text("Workout Schedule", style = MaterialTheme.typography.headlineSmall)
                Text(selectedDate, fontSize = 14.sp, color = Color.Gray)

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
                Icon(Icons.Default.Add, contentDescription = "Add")
            }

            if (showAddDialog) {
                AddWorkoutDialog(onDismiss = { showAddDialog = false }) { newWorkout ->
                    workouts.add(newWorkout)
                    showAddDialog = false
                }
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
    var time by remember { mutableStateOf("3:30 PM") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotBlank()) onAdd(WorkoutItem(name, time))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Schedule") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Workout Name") })
                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Time") })
            }
        }
    )
}

data class WorkoutItem(val name: String, val time: String)
