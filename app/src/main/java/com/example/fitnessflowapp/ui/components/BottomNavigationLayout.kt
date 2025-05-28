package com.example.fitnessflowapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.R

enum class BottomTab(val route: String, val iconRes: Int, val label: String) {
    Home("home", R.drawable.female_icon, "Home"),
    Profile("profile", R.drawable.female_icon, "Profile"),
    Stats("statistics", R.drawable.female_icon, "Statistics"),
    Photo("progress_photo", R.drawable.female_icon, "Progress")
}


@Composable
fun BottomNavigationDashboard(
    currentRoute: String,
    onTabSelected: (BottomTab) -> Unit
) {
    NavigationBar {
        BottomTab.entries.forEach { tab ->
            NavigationBarItem(
                selected   = (currentRoute == tab.route),
                onClick    = { onTabSelected(tab) },
                icon       = { Icon(painterResource(tab.iconRes), contentDescription = tab.label) },
                label      = { Text(tab.label) }
            )
        }
    }
}

@Composable
fun FitnessFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick         = onClick,
        containerColor  = MaterialTheme.colorScheme.primary,
        shape           = CircleShape,
        modifier        = Modifier.size(56.dp)
    ) {
        Icon(Icons.Default.Search, contentDescription = "FAB", tint = Color.White)
    }
}