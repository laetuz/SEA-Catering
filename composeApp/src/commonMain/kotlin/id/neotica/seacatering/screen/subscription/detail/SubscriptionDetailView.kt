package id.neotica.seacatering.screen.subscription.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import id.neotica.droidcore.component.button.NeoButton
import id.neotica.routes.Screen
import id.neotica.seacatering.dummy.packageList
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.image.ImageCoil
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.DarkPrimaryTransparent2
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubscriptionDetailView(
    navController: NavController,
    viewModel: SubscriptionDetailViewModel = koinViewModel()
) {
    val packageId = viewModel.packageId.collectAsState()
    Logger.d { "Package ID: $packageId" }
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
        packageList.find { it.id == packageId.value }?.let {
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
//                        Modifier.height(124.dp)
                    ) {
                        ImageCoil(
                            it.imageUrl,
                            scaleCustom = true,
                            contentScale = ContentScale.Fit,
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
                    }
                }
            }
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NeoButton("BUY THIS PLAN") {
                navController.navigate(Screen.SubscriptionPaymentScreen(packageId.value))
            }
        }
    }
}