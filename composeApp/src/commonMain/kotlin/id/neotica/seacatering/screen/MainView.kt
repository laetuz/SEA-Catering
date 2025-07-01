package id.neotica.seacatering.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.rickpository.res.MR
import id.neotica.routes.AlexandriaScreen
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.navigation.NavGraph
import id.neotica.ui.shared.components.navigation.CustomNavBar
import id.neotica.ui.shared.navigation.NavigationItem
import id.neotica.ui.shared.navigation.showExcept
import id.neotica.ui.shared.theme.BlackTransparent40
import id.neotica.ui.shared.theme.DarkBackground
import id.neotica.ui.shared.theme.DarkPrimary
import id.neotica.ui.shared.theme.NegativePrimary
import org.koin.compose.KoinContext

@Composable
fun MainView() {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = DarkPrimary,
            onPrimary = Color.White,
            secondary = Color.White,
            onSecondary = DarkPrimary,
            background = DarkBackground,
            onBackground = Color.White,
            tertiary = NegativePrimary,
            onTertiary = Color.White
        )
    ) {
        KoinContext {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val navItems = listOf(
                NavigationItem(
                    title = "Home",
                    icon = painterResource(MR.images.ic_home),
                    screen = RootScreen.HomeNav
                ),
                NavigationItem(
                    title = "Profile",
                    icon = painterResource(MR.images.ic_profile),
                    screen = Screen.ProfileScreen
                )
            )

            val screenSet = setOf(
                Screen.Splash,
                Screen.LoginScreen,
                Screen.RegisterScreen,
                Screen.VerifyEmailScreen,
                Screen.PrivacyPolicyScreen,
                Screen.TermsOfServiceScreen,
                Screen.DeleteAccountScreen,
                Screen.AboutScreen,
                Screen.AddChatScreen,
                Screen.UpdateAppScreen,
                Screen.CameraPreviewScreen,
                Screen.SearchUserScreen,
                Screen.FriendScreen,
                Screen.ResetPassword,
                Screen.Chat(),
                Screen.EditProfileScreen(),
            )

            val bottomBarExcludedRoutes = screenSet
            val includeRoute = navBackStackEntry.showExcept(bottomBarExcludedRoutes)

            Scaffold(
                containerColor = BlackTransparent40,
                modifier = Modifier
                    .fillMaxSize(),
                bottomBar = {
                    AnimatedVisibility (
                        visible = includeRoute,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it }),
                    ) {
                        CustomNavBar (
                            modifier = Modifier
                        ) {
                            navItems.map {
                                NavigationBarItem(
                                    colors = NavigationBarItemColors(
                                        selectedIconColor = Color.White,
                                        selectedTextColor = Color.White,
                                        selectedIndicatorColor = DarkPrimary,
                                        unselectedIconColor = Color.White,
                                        unselectedTextColor = Color.White,
                                        disabledIconColor = Color.Gray,
                                        disabledTextColor = Color.Gray
                                    ),
                                    selected = currentDestination?.hierarchy?.any { current ->
                                        current.hasRoute(it.screen::class)
                                    } == true,
                                    onClick = {
                                        navController.navigate(it.screen) {
                                            navController.graph.startDestinationId.let { route ->
                                                popUpTo(AlexandriaScreen.HomeScreen) {
                                                    saveState = true
                                                    inclusive = false
                                                }
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(painter = it.icon, contentDescription = it.title)
                                    },
                                    label = { Text(it.title) },
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                NavGraph(
                    navController = navController,
                    paddingValues = PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding() - WindowInsets.systemBars.asPaddingValues()
                            .calculateBottomPadding()
                    ),
                )
            }
        }
    }
}