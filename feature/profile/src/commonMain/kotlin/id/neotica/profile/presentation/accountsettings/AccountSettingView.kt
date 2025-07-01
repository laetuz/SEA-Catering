package id.neotica.profile.presentation.accountsettings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.routes.Screen
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.MenuItem
import id.neotica.ui.shared.theme.NegativePrimary

@Composable
fun AccountSettingView(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    BasicScaffold(
        topBarTitle = "Account Settings",
        horizontalAlignment = Alignment.CenterHorizontally,
        paddingValues = paddingValues,
        navController = navController,
        enableActionBar = true
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            MenuItem("Change Password") {
                toast.value = true
                toastMessage.value = "Please contact our support email to change password."
            }
            MenuItem(
                "Delete Account",
                textColor = NegativePrimary
            ) {
                navController.navigate(Screen.DeleteAccountScreen)
            }
        }

    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}