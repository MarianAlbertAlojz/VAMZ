package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.ui.components.DetailRow
import com.example.fitnessflowapp.ui.components.NotesRow

@Composable
fun AddExerciseScreen() {
    var isNotesExpanded by remember { mutableStateOf(false) }

    var exerciseName by remember { mutableStateOf("Squats") }
    var muscleGroup by remember { mutableStateOf("Legs") }
    var exerciseType by remember { mutableStateOf("Strength, Flexibility") }
    var difficulty by remember { mutableStateOf("Hard") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Your Exercise", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))


        DetailRow(label = "Difficulty", value = difficulty, onClick = {})


        NotesRow(
            isExpanded = isNotesExpanded,
            onToggle = { isNotesExpanded = !isNotesExpanded },
            text = notes,
            onTextChange = { notes = it }
        )

        DetailRow(label = "Exercise Name", value = exerciseName, onClick = {})
        DetailRow(label = "Target Muscle Group", value = muscleGroup, onClick = {})
        DetailRow(label = "Exercise type", value = exerciseType, onClick = {})
        DetailRow(label = "Add Photo", value = "", onClick = {})

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Add")
        }
    }
}
