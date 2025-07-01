package id.neotica.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.neotica.auth.presentation.login.LoginView
import id.neotica.auth.presentation.register.RegisterView
import id.neotica.auth.verify.VerifyEmailView
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.state.AppVariant

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    paddingValues: PaddingValues,
){
    navigation<RootScreen.AuthNav>(
        startDestination = Screen.LoginScreen,
    ) {
        composable<Screen.LoginScreen> {
            LoginView(navController)
        }
        composable<Screen.RegisterScreen> {
            RegisterView(navController)
        }
        composable<Screen.VerifyEmailScreen> {
            VerifyEmailView(navController, paddingValues)
        }
    }
}