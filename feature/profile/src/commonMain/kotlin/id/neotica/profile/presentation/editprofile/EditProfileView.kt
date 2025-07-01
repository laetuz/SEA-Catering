package id.neotica.profile.presentation.editprofile

//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.net.Uri
//import android.provider.Settings
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.ui.platform.LocalContext
//import id.neotica.profile.utils.compressUri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import id.neotica.profile.presentation.ProfileViewModel
import id.neotica.routes.Screen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
expect fun EditProfileView(
    navController: NavController, paddingValues: PaddingValues,
    viewModel: ProfileViewModel = koinViewModel(),
    args: Screen.EditProfileScreen,
)