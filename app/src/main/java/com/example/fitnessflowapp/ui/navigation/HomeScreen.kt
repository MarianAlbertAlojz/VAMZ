package com.example.fitnessflowapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.ui.components.BottomNavigationDashboard
import com.example.fitnessflowapp.ui.components.FitnessFab
import com.example.fitnessflowapp.ui.components.InfoActionCard
import com.example.fitnessflowapp.ui.components.WorkoutProgressChart
import com.example.fitnessflowapp.ui.viewmodel.ProfileViewModel

//strings ok
//komentare
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route
    val bmi = viewModel.calculateBmi()
    val bmiStatus = bmi?.let { viewModel.bmiStatus(it) } ?: stringResource(R.string.bmi_no_data)

    val latestWorkouts = remember {
        listOf(
            context.getString(R.string.fullbody_workout_title) to context.getString(R.string.calories_180_20min),
            context.getString(R.string.lowerbody_workout_title) to context.getString(R.string.calories_200_30min),
            context.getString(R.string.ab_workout_title) to context.getString(R.string.calories_180_20min)
        )
    }

    val sections = remember {
        listOf(
            context.getString(R.string.section_sleep),
            context.getString(R.string.section_steps),
            context.getString(R.string.section_weight),
            context.getString(R.string.section_vitals)
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationDashboard(
                currentRoute = currentRoute,
                onTabSelected = { selectedTab ->
                    navController.navigate(selectedTab.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        floatingActionButton = { FitnessFab { } },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(R.string.welcome_back), fontSize = 14.sp, color = Color.Gray)
                    Text("Marian Bobcek", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }

            item {
                bmi?.let {
                    BmiCard(bmiValue = it, status = bmiStatus)
                } ?: Text(stringResource(R.string.bmi_not_available))
            }

            item {
                WorkoutProgressChart(
                    data = listOf(40f, 60f, 30f, 50f, 90f, 45f, 70f),
                    selectedDayIndex = 4,
                    workoutName = stringResource(R.string.upperbody_workout_title)
                )
            }

            item {
                InfoActionCard(
                    title = stringResource(R.string.workout_tracker_home_title),
                    buttonText = stringResource(R.string.check),
                    onClick = { navController.navigate(Screen.WorkoutTrackerHome.route) }
                )
            }

            item {
                Text(stringResource(R.string.latest_workout), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            itemsIndexed(latestWorkouts) { _, (title, info) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(info, fontSize = 12.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(progress = 0.5f, modifier = Modifier.fillMaxWidth())
                    }
                }
            }

            itemsIndexed(sections) { _, section ->
                InfoActionCard(
                    title = section,
                    buttonText = stringResource(R.string.check),
                    onClick = {}
                )
            }

            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun BmiCard(bmiValue: Double, status: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(stringResource(R.string.bmi_title), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(status, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { }) {
                    Text(stringResource(R.string.view_more))
                }
            }
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = bmiValue.toString(), fontWeight = FontWeight.Bold)
            }
        }
    }
}

