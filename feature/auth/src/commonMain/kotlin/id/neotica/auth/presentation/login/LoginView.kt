package id.neotica.auth.presentation.login

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.droidcore.component.textfield.NeoTextField
import id.neotica.rickpository.res.MR
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.ui.shared.PasswordTextField
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.LoadingIndicator
import id.neotica.ui.shared.components.NeoButton
import id.neotica.ui.shared.theme.DarkPrimary
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.animation.core.rememberInfiniteTransition as rememberInfiniteTransition1

@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()) {

    var isButtonPressed by remember { mutableStateOf(false) }
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loading.collect {
            isLoading = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loginResult.collect {
            Logger.d("neouser") { "loginview: ${toastMessage.value}" }
            toast.value = true
            toastMessage.value = it
        }
    }

    fun navigateTarget() = navController.navigate(RootScreen.HomeNav) {
        popUpTo(RootScreen.HomeNav) {
            inclusive = true
        }
        launchSingleTop
    }

    val infiniteTransition = rememberInfiniteTransition1()

    // Animate a float value from 0f to 360f to represent rotation
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    BasicScaffold("Login", topBarVisibility = false, navController = navController) {

        Image(
            painterResource(MR.images.logo_seacat),
            contentDescription = "SEACatering Logo",
            contentScale = ContentScale.Crop,
            modifier =  Modifier
                .fillMaxWidth().size(180.dp)
        )
        Text(
            text = "SEA Catering",
            fontSize = FontSize.Big
        )
        Text(
            text = "Healthy Meals, Anytime, Anywhere",
            fontSize = FontSize.Medium
        )
        Box(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Column {
                Box(
                    modifier = Modifier.weight(1f),
                ) {
                    Column {
                        NeoTextField(value = emailValue, label = "Email")
                        Spacer(modifier = Modifier.padding(4.dp))
                        PasswordTextField(value = passwordValue, placeholder = "Password")
                    }
                }
                Column(
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    NeoButton(
                        text = "Login",
                        enabled = emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty(),
                        maxWidth = true
                    ) {
                        isButtonPressed = true
                        viewModel.login(emailValue.value, passwordValue.value) {
                            navigateTarget()
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Haven't made an account? ",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = FontSize.Smaller,
                        )
                        Text(
                            color = DarkPrimary,
                            fontSize = FontSize.Smaller,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = "Register.",
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.RegisterScreen)
                            }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Forgot Password? ",
                            overflow = TextOverflow.Ellipsis,
                            fontSize = FontSize.Smaller,
                        )
                        Text(
                            color = DarkPrimary,
                            fontSize = FontSize.Smaller,
                            text = "Reset Password.",
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.ResetPassword)
                            }
                        )
                    }
                    Spacer(Modifier.padding(8.dp))
                }
            }
            if (isLoading) Box(
                Modifier
                    .fillMaxSize()
                    .clickable(enabled = true) { },
            ) { LoadingIndicator() }
        }
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}