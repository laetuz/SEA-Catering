package id.neotica.seacatering.screen.subscription

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import id.neotica.droidcore.component.button.NeoButton
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.dummy.packageList
import id.neotica.seacatering.screen.home.HomeViewModel
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.image.ImageCoil
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.DarkPrimaryTransparent2
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubscriptionView(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
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
        "Subscription", navController = navController,
        enableActionBar = true,
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
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = rememberLazyListState()
        ) {
            items(packageList) {
                Card(
                    Modifier
//                        .fillMaxWidth()
                        .clickable {
                            // dont forget to add logic
                        }
                        .wrapContentHeight()
                        .padding(2.dp),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = DarkPrimaryTransparent2,
                        contentColor = Color.White
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            Modifier.height(124.dp)
                        ) {
                            ImageCoil(
                                it.imageUrl,
                                scaleCustom = true,
                                contentScale = ContentScale.Crop,
                                fillMaxWidth = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column(
                            Modifier.padding(4.dp)
                        ) {
                            Text(
                                it.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                "${it.description}",
                                fontSize = FontSize.Small,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                "FEATURES:",
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = FontSize.Medium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                "${it.features}",
                                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                                fontSize = FontSize.Small,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                "Rp ${it.price}",
                                fontWeight = FontWeight.Bold,
                                fontSize = FontSize.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(it.id.toString())
                            NeoButton("BUY THIS PLAN") {
                                Logger.d { "BUY THIS PLAN: ${it.id}" }
                                navController.navigate(Screen.SubscriptionDetailScreen(it.id))
                            }
                        }
                    }
                }
            }
        }
    }

}