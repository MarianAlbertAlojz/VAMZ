
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.ui.components.BottomNavigationDashboard
import com.example.fitnessflowapp.ui.components.FitnessFab
import com.example.fitnessflowapp.ui.viewmodel.ProfileViewModel


//komentare
//zmena podla figmy
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Profile.route

    Scaffold(
        bottomBar = {
            BottomNavigationDashboard(
                currentRoute = currentRoute,
                onTabSelected = { selectedTab ->
                    navController.navigate(selectedTab.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        floatingActionButton = {
            FitnessFab {  }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        val profile = viewModel.userProfile.value

        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            if (profile == null) {
                Text("No profile found.")
            } else {
                Text("Gender: ${profile.gender}")
                Text("Age: ${profile.age}")
                Text("Weight: ${profile.weight}")
                Text("Goal: ${profile.goal}")
                Text("Activity Level: ${profile.activityLevel}")
                Text("Full Name: ${profile.fullName}")
                Text("Nickname: ${profile.nickname}")
                Text("Email: ${profile.email}")
                Text("Phone: ${profile.phone}")
                Text("Avatar Uri: ${profile.avatarUri}")
            }
        }
    }
}
