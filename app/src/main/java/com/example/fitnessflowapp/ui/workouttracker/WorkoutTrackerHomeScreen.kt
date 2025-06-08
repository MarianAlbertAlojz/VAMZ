package com.example.fitnessflowapp.ui.workouttracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.InfoActionCard
import com.example.fitnessflowapp.ui.components.WorkoutCard
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader
import com.example.fitnessflowapp.ui.components.WorkoutProgressChart

//strings done
//komentare
@Composable
fun WorkoutTrackerScreen(
    onActionClick: (String) -> Unit,
    onWorkoutClick: (String) -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current
    val progressData = listOf(40f, 60f, 30f, 50f, 90f, 45f, 70f)
    val selectedDayIndex = 4
    val title = ScreenTitleProvider.getTitle(ScreenType.TRACKER)

    Column(modifier = Modifier.fillMaxSize()) {

        // header hore
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
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                WorkoutProgressChart(
                    data = progressData,
                    selectedDayIndex = selectedDayIndex,
                    workoutName = context.getString(R.string.upperbody_workout_title)
                )
            }

            item {
                InfoActionCard(
                    title = stringResource(id = R.string.daily_workout_schedule),
                    buttonText = stringResource(id = R.string.check)
                ) {
                    onActionClick("schedule")
                }
            }

            item {
                InfoActionCard(
                    title = stringResource(id = R.string.add_your_workout_title),
                    buttonText = stringResource(id = R.string.add)
                ) {
                    onActionClick("workout_add_workout")
                }
            }

            item {
                InfoActionCard(
                    title = stringResource(id = R.string.all_workouts_title),
                    buttonText = stringResource(id = R.string.check)
                ) {
                    onActionClick("workout_tracker_workouts")
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.train_desc),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            items(3) { index ->
                when (index) {
                    0 -> WorkoutCard(
                        title = stringResource(id = R.string.fullbody_workout_title),
                        exerciseCount = 11,
                        duration = stringResource(id = R.string.duration_32mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("fullbody") }
                    )

                    1 -> WorkoutCard(
                        title = stringResource(id = R.string.lowerbody_workout_title),
                        exerciseCount = 12,
                        duration = stringResource(id = R.string.duration_40mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("lowerbody") }
                    )

                    2 -> WorkoutCard(
                        title = stringResource(id = R.string.ab_workout_title),
                        exerciseCount = 14,
                        duration = stringResource(id = R.string.duration_20mins),
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
