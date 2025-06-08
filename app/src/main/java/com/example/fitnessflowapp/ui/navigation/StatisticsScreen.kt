package com.example.fitnessflowapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.ui.components.BottomNavigationDashboard
import com.example.fitnessflowapp.ui.components.FitnessFab


//komentare
//zmena podla figmy
@Composable
fun StatisticsScreen(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Statistic.route

    Scaffold(
        bottomBar = {
            BottomNavigationDashboard(
                currentRoute = currentRoute,
                onTabSelected = { selectedTab ->
                    navController.navigate(selectedTab.route) {
                        popUpTo(currentRoute) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        },
        floatingActionButton = {
            FitnessFab {  }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Welcome Back,", style = MaterialTheme.typography.titleSmall)
            Text(
                "Marian Bobcek",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

        }
    }
}


