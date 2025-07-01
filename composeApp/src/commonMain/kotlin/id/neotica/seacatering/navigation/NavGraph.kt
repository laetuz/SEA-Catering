package id.neotica.seacatering.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.neotica.auth.authNavGraph
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.screen.home.HomeView
import id.neotica.seacatering.screen.subscription.SubscriptionView
import id.neotica.seacatering.screen.subscription.confirmation.SubscriptionDetailView

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.HomeNav,
        enterTransition = { fadeIn(tween(100)) },
        popEnterTransition = { EnterTransition.None },
        exitTransition = { fadeOut(tween(100)) },
        popExitTransition = { ExitTransition.None }
    ) {
        navigation<RootScreen.HomeNav>(
            startDestination = Screen.HomeScreen
        ) {
            composable<Screen.HomeScreen> {
                HomeView(
                    navController = navController,
                )
            }

            //subscription
            composable<Screen.SubscriptionScreen> {
                SubscriptionView(
                    navController = navController,
                )
            }
            composable<Screen.SubscriptionDetailScreen> {
                SubscriptionDetailView(
                    navController = navController,
                )
            }
        }
        authNavGraph(navController, paddingValues)
        profileNavGraph(navController, paddingValues)
    }
}