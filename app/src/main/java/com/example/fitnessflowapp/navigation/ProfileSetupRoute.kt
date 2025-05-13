import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.data.repository.SetupPageRepository
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.navigation.SetupStep
import com.example.fitnessflowapp.ui.setup.FillYourProfileScreen

/*
* tu budem musiet spravit to ze sa to bude dat aj editovat teda daky crop fotky
* zaroven to dako spravit tak aby mohol toto byt daky kvazi komponent pri editacii fotky aj normalnom screene profilu
* budem musiet dotiahnut viewModel teda cely MVVM
* */
@Composable
fun ProfileSetupRoute(navController: NavHostController) {
    val context = LocalContext.current
    val page = SetupPageRepository.getPages(context)[SetupStep.Profile.pageIndex]

    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { }
    }

    FillYourProfileScreen(
        title = page.title,
        description = page.description,
        onBack = { navController.popBackStack() },
        onEditPictureClick = { pickImage.launch("image/*") },
        onNext = {
            navController.navigate(Screen.Setup.route) {
                popUpTo(Screen.SetupFillProfile.route) { inclusive = true }
            }
        }
    )
}
