package id.neotica.profile.presentation.inputprofile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import id.neotica.domain.NeoUser
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.droidcore.component.textfield.NeoTextField
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.NeoButton
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputProfileView(
    navController: NavController, paddingValues: PaddingValues,
    viewModel: InputProfileViewModel = koinViewModel(),
) {
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val displayName = remember { mutableStateOf("") }
    val profile = viewModel.profile.collectAsState()
//    val message = viewModel.

//    LaunchedEffect(errorMessage) {
//        errorMessage?.let {
////            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.profileChannel.collect {
            when(it) {
                is InputProfileViewModel.ProfileState.OnError -> {
                    toastMessage.value = it.message
                    toast.value = true
                }
                is InputProfileViewModel.ProfileState.OnNavigate -> {
                    it.route?.let {
                        navController.navigate(it)
                    }
                }
            }
        }
    }

    BasicScaffold(
        topBarTitle = "Input profile") {
        NeoTextField(username, label = "Username")
        NeoTextField(email, label = "Email")
        NeoTextField(displayName, label = "Display Name")

        NeoButton("Update") {
//            viewModel.fillProfile(
//                NeoUser(
//                    username = username.value,
//                    email = email.value,
//                    displayName = displayName.value,
//                    firebaseId = "",
//                    createdAt = 0
//                )
//            )
        }
    }

    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}