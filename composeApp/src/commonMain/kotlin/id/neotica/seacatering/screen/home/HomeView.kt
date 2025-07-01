package id.neotica.seacatering.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
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
import id.neotica.droidcore.component.button.NeoButton
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.dummy.menuOne
import id.neotica.seacatering.dummy.menuTwo
import id.neotica.seacatering.dummy.model.FoodMenu
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.image.ImageCoil
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.DarkPrimaryTransparent2
import id.neotica.ui.shared.theme.FontSize
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
    BasicScaffold(
        "Home", navController = navController,
        trailingIcon = {
            DotsMenuDropDown(
                item = listOf(
                    DotsMenuItem("Contact us") {
                        navController.navigate(Screen.AboutScreen)
                    },
                    DotsMenuItem("Add Review") {
                        navController.navigate(Screen.ReviewScreen)
                    }
                ),
            )
        }
    ) {
        Text(
            text = "SEA Catering",
            fontSize = FontSize.Bigger,
            modifier = Modifier.padding(horizontal = 16.dp)

        )
        Text(
            text = "Healthy Meals, Anytime, Anywhere",
            fontSize = FontSize.Medium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.padding(16.dp))
        HorizontalMenus(
            title = "Our fresh menus!",
            endTitle = "More Foods.. >>>",
            menuList = menuOne
        )
        HorizontalMenus(
            title = "Our picked menus!",
            endTitle = "More Foods.. >>>",
            menuList = menuTwo
        )


    }
}

@Composable
fun HorizontalMenus(
    title: String,
    endTitle: String,
    menuList: List<FoodMenu>
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.Normal
        )
        Text(
            text = endTitle,
            modifier = Modifier.clickable {  },
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.Normal
        )
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = rememberLazyListState()
    ) {
        items(menuList.take(8)) { item ->
            Card(
                Modifier
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
                Column(Modifier.width(144.dp)) {
                    Box(
                        Modifier.height(124.dp)
                    ) {
                        ImageCoil(
                            item.imageUrl,
                            scaleCustom = true,
                            contentScale = ContentScale.Crop,
                            fillMaxWidth = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(Modifier.padding(4.dp)) {
                        Text(
                            item.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            "${item.description}",
                            fontSize = FontSize.Small,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            "Rp ${item.price}",
                            fontWeight = FontWeight.Bold,
                            fontSize = FontSize.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}