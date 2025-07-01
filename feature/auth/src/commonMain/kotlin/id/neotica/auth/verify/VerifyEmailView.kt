package id.neotica.auth.verify

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.routes.Screen
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.NeoButton
import id.neotica.ui.shared.theme.DarkPrimary
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VerifyEmailView(
    navController: NavController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: VerifyEmailViewModel = koinViewModel()
) {
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (viewModel.isUserVerified()) {
            viewModel.logout()
            navController.navigate(Screen.LoginScreen)
        } else
            viewModel.sendEmailVerification()
    }
    BasicScaffold (
        "Verify email",
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.padding(4.dp)) {
            Column {
                Box(Modifier.weight(1f)) {
                    Column {
                        Text(
                            text = "You have been successfully registered. " +
                                    "Now kindly verify your email address " +
                                    "with the link that we had just sent to your email address.",
                        )
                    }
                }
                Row(
                    Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NeoButton(
                        "My Email is verified",
                        maxWidth = true
                    ) {
                        viewModel.logout()
                        navController.navigate(Screen.LoginScreen)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Didn't receive an email? ",
                        fontSize = 14.sp,
                    )
                    Text(
                        color = DarkPrimary,
                        fontSize = 14.sp,
                        text = "Resend email.",
                        modifier = Modifier.clickable {
                            viewModel.sendEmailVerification()
                            toastMessage.value = "Email sent."
                            toast.value = true
                        }
                    )
                }
                Spacer(Modifier.padding(8.dp))
            }
        }
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}