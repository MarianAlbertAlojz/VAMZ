package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.static.ScreenTitleProvider
import com.example.fitnessflowapp.static.ScreenType
import com.example.fitnessflowapp.ui.components.InfoActionCard
import com.example.fitnessflowapp.ui.components.WorkoutCard
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader
import com.example.fitnessflowapp.ui.components.WorkoutProgressChart

@Composable
fun WorkoutTrackerScreen(
    onActionClick: (String) -> Unit,
    onWorkoutClick: (String) -> Unit
) {
    val progressData = listOf(40f, 60f, 30f, 50f, 90f, 45f, 70f)
    val selectedDayIndex = 4
    val title = ScreenTitleProvider.getTitle(ScreenType.TRACKER)

    Column(modifier = Modifier.fillMaxSize()) {

        // header hore
        WorkoutDetailsHeader(
            title = title,
            onBackClick = { /* back */ },
            onOptionsClick = { /* menu? */ }
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                WorkoutProgressChart(
                    data = progressData,
                    selectedDayIndex = selectedDayIndex,
                    workoutName = "Upperbody Workout"
                )
            }

            item {
                InfoActionCard(title = "Daily Workout Schedule", buttonText = "Check") {
                    onActionClick("schedule")
                }
            }

            item {
                InfoActionCard(title = "Add Your Workout", buttonText = "Add") {
                    onActionClick("workout_add_workout")
                }
            }

            item {
                InfoActionCard(title = "All Workouts", buttonText = "Check") {
                    onActionClick("workout_tracker_workouts")
                }
            }

            item {
                Text(
                    text = "What Do You Want to Train",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            items(3) { index ->
                when (index) {
                    0 -> WorkoutCard(
                        title = "Fullbody Workout",
                        exerciseCount = 11,
                        duration = "32mins",
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("fullbody") }
                    )

                    1 -> WorkoutCard(
                        title = "Lowerbody Workout",
                        exerciseCount = 12,
                        duration = "40mins",
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("lowerbody") }
                    )

                    2 -> WorkoutCard(
                        title = "AB Workout",
                        exerciseCount = 14,
                        duration = "20mins",
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("abs") }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
