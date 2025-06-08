
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.navigation.SetupStep
import com.example.fitnessflowapp.static.SetupPages
import com.example.fitnessflowapp.ui.setup.FillYourProfileScreen
import com.example.fitnessflowapp.ui.viewmodel.SetupViewModel

/*
* poznamky mimo
* tu budem musiet spravit to ze sa to bude dat aj editovat teda daky crop fotky
* zaroven to dako spravit tak aby mohol toto byt daky kvazi komponent
* pri editacii fotky aj normalnom screene profilu
*
* */


/**
 * reprezentuje posledny krok v onboarding procese – vyplnenie profilu
 * logika na vyber profilovej fotky, pracu s ViewModelom a navigaciu po dokonceni
 *
 * nacitava text z objektu SetupPages podla indexu kroku
 * reaguje na zmenu textovych poli a kliknutie na tlacidlo ulozit
 * po potvrdeni ulozi profil a presmeruje pouzivatela na domovsku obrazovku
 *
 * @param navController controller pre prechod medzi obrazovkami
 * @param vm ViewModel zodpovedny za stav formulara profilu
 */
@Composable
fun ProfileSetupRoute(
    navController: NavHostController,
    vm : SetupViewModel
) {
    val form by vm.formState.collectAsState()
    val context = LocalContext.current
    val page = SetupPages.getPages(context)[SetupStep.Profile.pageIndex]

    //vyber obrazka z galerie
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { vm.updateAvatarUri(it) }
    }

    FillYourProfileScreen(
        title = page.title,
        description = page.description,
        form = form,
        onEditPictureClick = { pickImage.launch("image/*") },
        onFieldChanged = { field, value ->
            vm.updateProfileField(field, value)
        },
        onBack = { navController.popBackStack() },
        onNext = {
            vm.saveAllAndFinish()
            navController.navigate(Screen.Home.route) {
                popUpTo(SetupStep.Setup.route) { inclusive = true }
            }
        }
    )
}
