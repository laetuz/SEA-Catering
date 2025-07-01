package id.neotica.auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.droidcore.component.textfield.NeoTextField
import id.neotica.routes.Screen
import id.neotica.ui.shared.PasswordTextField
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.LoadingIndicatorBool
import id.neotica.ui.shared.components.NeoButton
import id.neotica.ui.shared.theme.DarkPrimary
import id.neotica.ui.shared.theme.FontSize
import id.neotica.ui.shared.theme.NegativePrimary
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterView(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel()
    ) {
    val nameValue = remember { mutableStateOf("") }
    val nameNoWhiteSpace = remember(nameValue.value) {
        !nameValue.value.contains(Regex("\\s"))
    }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    var isButtonPressed by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var isUserExist by remember { mutableStateOf(false) }
    var onValueChange by remember { mutableStateOf(false) }
    var checkEnabled by remember { mutableStateOf(false) }

    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loading.collect {
            isLoading = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.registerResult.collect {
            toast.value = true
            toastMessage.value = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.doesUserExist.collect { isUserExist = !it }
    }

    LaunchedEffect(onValueChange) {
        viewModel.checkUsername(nameValue.value)
    }

    BasicScaffold(
        "Register",
        true,
        navController = navController
    ) {
        Box(Modifier.padding(vertical = 4.dp, horizontal = 16.dp)) {
            Column {
                LoadingIndicatorBool(isLoading = isLoading) {
                    Box(Modifier.weight(1f)) {
                        Column {
                            NeoTextField(
                                value = nameValue, label = "Username",
                                onValueChange = {
                                    onValueChange = !onValueChange
                                }
                            )
                            val errorMessage = when {
                                viewModel.blockedWord.any { nameValue.value.contains(it, ignoreCase = true) } -> "Username contains prohibited words."
                                nameValue.value.length < 3 && nameValue.value.isNotEmpty() -> "Username must be at least 3 characters."
                                !isUserExist  -> "Username is already used."
                                else -> null
                            }

                            errorMessage?.let {
                                Text(
                                    it,
                                    color = NegativePrimary,
                                    fontSize = FontSize.Small
                                )
                            }
                            NeoTextField(value = emailValue, label = "Email")
                            Spacer(Modifier.padding(4.dp))
                            PasswordTextField(value = passwordValue, placeholder = "Password")
                            Spacer(Modifier.padding(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    color = DarkPrimary,
                                    fontSize = 14.sp,
                                    text = "Privacy Policy",
                                    modifier = Modifier.clickable {
                                        navController.navigate(Screen.PrivacyPolicyScreen)
                                    }
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    color = DarkPrimary,
                                    fontSize = 14.sp,
                                    text = "Terms of Service",
                                    modifier = Modifier.clickable {
                                        navController.navigate(Screen.TermsOfServiceScreen)
                                    }
                                )
                            }
                            Spacer(Modifier.padding(16.dp))
                            Text("By registering, you are already accepting the Terms and Service and Privacy Policy of this app.")
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Yes, I agree.")
                                Checkbox(checkEnabled, {checkEnabled = !checkEnabled})
                            }
                        }
                    }
                    Row(
                        Modifier.padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            val nullCheck = when {
                                nameValue.value.isEmpty() -> "Please fill all the fields."
                                !isUserExist -> "Username is already used."
                                !checkEnabled -> "Please agree to the terms and conditions."
                                else -> null
                            }
                            nullCheck?.let {
                                Text(
                                    it,
                                    color = NegativePrimary,
                                    fontSize = FontSize.Small
                                )
                            }
                            NeoButton(
                                "Register",
                                maxWidth = true,
                                enabled = nameValue.value.isNotEmpty()
                                        && emailValue.value.isNotEmpty()
                                        && passwordValue.value.isNotEmpty()
                                        && nameValue.value.length >= 3
                                        && nameNoWhiteSpace
                                        && checkEnabled
                                        && isUserExist,
                            ) {
                                isButtonPressed = true
                                viewModel.register(nameValue.value.lowercase(), emailValue.value, passwordValue.value) {
                                    navController.navigate(Screen.HomeScreen)
                                    toast.value = true
                                    toastMessage.value = "Success registered ${nameValue.value}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}