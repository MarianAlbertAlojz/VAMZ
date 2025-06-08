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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.WorkoutCard
import com.example.fitnessflowapp.ui.components.WorkoutDetailsHeader

//strings done
//komentare
@Composable
fun AllWorkoutsScreen(
    onWorkoutClick: (String) -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current
    val title = ScreenTitleProvider.getTitle(ScreenType.ALL_WORKOUTS)

    Column(modifier = Modifier.fillMaxSize()) {
        WorkoutDetailsHeader(
            title = title,
            onBackClick = { navController.popBackStack() },
            onOptionsClick = { /* menu? */ }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.train_desc),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            items(6) { index ->
                when (index) {
                    0 -> WorkoutCard(
                        title = stringResource(id = R.string.fullbody_workout_title),
                        exerciseCount = 11,
                        duration = context.getString(R.string.duration_32mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("fullbody") }
                    )

                    1 -> WorkoutCard(
                        title = stringResource(id = R.string.lowerbody_workout_title),
                        exerciseCount = 12,
                        duration = context.getString(R.string.duration_40mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("lowerbody") }
                    )

                    2 -> WorkoutCard(
                        title = stringResource(id = R.string.ab_workout_title),
                        exerciseCount = 14,
                        duration = context.getString(R.string.duration_20mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("abs") }
                    )

                    3 -> WorkoutCard(
                        title = stringResource(id = R.string.fullbody_workout_title),
                        exerciseCount = 11,
                        duration = context.getString(R.string.duration_32mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("fullbody") }
                    )

                    4 -> WorkoutCard(
                        title = stringResource(id = R.string.lowerbody_workout_title),
                        exerciseCount = 12,
                        duration = context.getString(R.string.duration_40mins),
                        imageResId = R.drawable.female_icon,
                        onClick = { onWorkoutClick("lowerbody") }
                    )

                    5 -> WorkoutCard(
                        title = stringResource(id = R.string.ab_workout_title),
                        exerciseCount = 14,
                        duration = context.getString(R.string.duration_20mins),
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