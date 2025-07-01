package id.neotica.seacatering.screen.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.droidcore.component.textfield.NeoTextField
import id.neotica.rickpository.res.MR
import id.neotica.rickpository.res.MR.images.ic_star_filled
import id.neotica.rickpository.res.MR.images.ic_star_outline
import id.neotica.rickpository.res.MR.images.three_dots
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.screen.home.HomeViewModel
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.NeoButton
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ReviewView(
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
        "Add Review", navController = navController,
        enableActionBar = true,
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp)
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Share your experience with us!",
                fontSize = FontSize.Bigger,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Your feedback helps us to improve our service.",
                fontSize = FontSize.Medium
            )

            var rating by remember { mutableStateOf(3) }
            Text("Rating: $rating out of 5")
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StarRating(
                    rating = rating,
                    onRatingChanged = {
                        rating = it
                    }
                )

                val reviewText = remember { mutableStateOf("") }
                NeoTextField(
                    value = reviewText,
                    label = "Share your experience using this app!"
                )
                Spacer(Modifier.padding(8.dp))
                NeoButton("SHARE")
            }

        }
    }
}

@Composable
fun StarRating(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    maxStars: Int = 5
) {
    Row(modifier = modifier) {
        for (i in 1..maxStars) {
            Icon(
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
                    .minimumInteractiveComponentSize()
                    .combinedClickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = true, color = White),
                        onClick = {
                            onRatingChanged(i)
//                            expanded.value = !expanded.value
                        },
                        onLongClick = {  },
                    ),
                painter =  if (i <= rating) painterResource(ic_star_filled) else painterResource(ic_star_outline),
                contentDescription = "Star $i",
                tint = Yellow
            )
//            Text(
//                text = if (i <= rating) "star" else "unstar",
//                modifier = Modifier
//                    .size(32.dp)
//                    .clickable { onRatingChanged(i) }
//                    .padding(4.dp),
//            )
        }
    }
}