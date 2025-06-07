package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.ui.components.DetailRow
import com.example.fitnessflowapp.ui.components.SetRow


@Composable
fun ExerciseInfoScreen() {
    var sets by remember { mutableStateOf(mutableListOf(Triple(1, 12, 50))) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Exercise Information", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        Text("Squats", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        DetailRow(label = "How It Went?", value = "Good", onClick = { /* tu spravit hodnotenie */ })

        Spacer(modifier = Modifier.height(12.dp))

        Text("Custom Repetitions And Weights", style = MaterialTheme.typography.labelMedium)

        Spacer(modifier = Modifier.height(12.dp))

        sets.forEachIndexed { index, (set, reps, weight) ->
            SetRow(
                index = index,
                set = set,
                reps = reps,
                weight = weight,
                onRemove = {
                    sets = sets.toMutableList().apply { removeAt(index) }
                },
                onChange = { newSet -> sets = sets.toMutableList().apply { set(index, newSet) } }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        FloatingActionButton(
            onClick = {
                sets = sets.toMutableList().apply { add(Triple(1, 10, 0)) }
            },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Set")
        }

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = { /* potom ulozenie*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Save")
        }
    }
}
