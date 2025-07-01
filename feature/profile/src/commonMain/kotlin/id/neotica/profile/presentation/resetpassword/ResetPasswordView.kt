package id.neotica.profile.presentation.resetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.droidcore.component.textfield.NeoTextField
import id.neotica.profile.presentation.ProfileViewModel
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.NeoButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResetPasswordView(
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel()
    ) {
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    BasicScaffold(
        topBarTitle = "Reset Password",
        true,
        navController = navController,
    ) {

        val email = remember { mutableStateOf("") }

        Column(
            Modifier.padding(horizontal = 16.dp)
        ) {
            Text("Please type your email address.")
            Spacer(Modifier.weight(1f))
            NeoTextField(email)
            Spacer(Modifier.padding(4.dp))
            Row {
                NeoButton(
                    text = "Reset password",
                    maxWidth = true,
                    enabled = email.value.isNotEmpty(),
                ) {
                    viewModel.resetPassword(email = email.value)
                    toast.value = true
                    toastMessage.value = "Please check your email inbox."
                }
            }
        }
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}