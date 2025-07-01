package id.neotica.profile.presentation.editprofile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import id.neotica.profile.presentation.ProfileViewModel
import id.neotica.routes.Screen

@OptIn(markerClass = [ExperimentalMaterial3Api::class])
@Composable
actual fun EditProfileView(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel,
    args: Screen.EditProfileScreen
) {
    Text("You can edit profile on the Android app.")
}