package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.static.ScreenTitleProvider
import com.example.fitnessflowapp.static.ScreenType
import com.example.fitnessflowapp.ui.components.ExerciseItem
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader


data class Exercise(
    val name: String,
    val imageRes: Int,
    val onClick: () -> Unit
)

@Composable
fun AddWorkoutScreen() {
    val exercises = listOf(
        Exercise("Squats", R.drawable.female_icon) { /* TODO */ },
        Exercise("Bench", R.drawable.female_icon) { /* TODO */ },
        Exercise("Deadlifts", R.drawable.female_icon) { /* TODO */ }
    )
    val title = ScreenTitleProvider.getTitle(ScreenType.ADD_WORKOUT)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_add),
                    contentDescription = "Add Exercise"
                )
            }
        },
        bottomBar = {
            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize()) {
            WorkoutDetailsHeader(
                title = title,
                onBackClick = { /* back */ },
                onOptionsClick = { /* menu? */ }
            )

            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Add Your Workout", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(16.dp))


                Text("Workout Details", style = MaterialTheme.typography.labelMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Default Workout Name", style = MaterialTheme.typography.titleMedium)

                LazyColumn {
                    items(exercises.size) { index ->
                        val (name, img, onClick) = exercises[index]
                        ExerciseItem(name = name, imageRes = img, onInfoClick = onClick)
                    }
                }
            }
        }
    }
}