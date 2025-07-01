package id.neotica.seacatering.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.ui.shared.components.BasicScaffold
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
//        isPressed = false
        if (!viewModel.isLoggedIn()) {
            navController.navigate(RootScreen.AuthNav) {
                popUpTo(RootScreen.HomeNav) {
                    inclusive = true
                }
                launchSingleTop
            }

        }
    }
    BasicScaffold("Home", navController = navController) {
        Text("HomeView")
    }

}