package id.neotica.seacatering.screen.menu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.screen.home.HomeViewModel
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuView(
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
    BasicScaffold(
        "Home", navController = navController,
        trailingIcon = {
            DotsMenuDropDown(
                item = listOf(
                    DotsMenuItem("Contact us") {
                        navController.navigate(Screen.AboutScreen)
                    },
                    DotsMenuItem("Add Review") {
                        navController.navigate(Screen.AboutScreen)
                    }
                ),
            )
        }
    ) {
        Text(
            text = "SEA Catering",
            fontSize = FontSize.Bigger
        )
        Text(
            text = "Healthy Meals, Anytime, Anywhere",
            fontSize = FontSize.Medium
        )
    }

}