package id.neotica.seacatering.screen.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.rickpository.res.MR
import id.neotica.routes.RootScreen
import id.neotica.routes.Screen
import id.neotica.seacatering.screen.home.HomeViewModel
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.components.DropdownItem
import id.neotica.ui.shared.components.topbar.DotsMenuDropDown
import id.neotica.ui.shared.components.topbar.DotsMenuItem
import id.neotica.ui.shared.theme.DarkPrimary
import id.neotica.ui.shared.theme.DarkPrimaryTransparent2
import id.neotica.ui.shared.theme.FontSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdminView(
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
        "Admin",
        navController = navController,
        paddingValues = PaddingValues(horizontal = 16.dp),
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
        Column(
            Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Select day.")
            val dropDown = remember { mutableStateOf(false) }
            PageDropdown2(
                currentValue = 1,
                count = 5,
                top = dropDown.value,
                onValueChange = {}
            )
        }

        LiquidSection(
            title = "Active Subscription",
            value = "10"
        )
        LiquidSection(
            title = "New Subscription:",
            value = "30"
        )
        LiquidSection(
            title = "Monthly Recurring Revenue:",
            value = "Rp. 12.000.000"
        )
        LiquidSection(
            title = "Cancelled Subscription:",
            value = "3"
        )

    }
}

@Composable
fun LiquidSection(
    title: String,
    value: String
) {
    Row(
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Card(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(4f),
            colors = CardDefaults.cardColors().copy(
                containerColor = DarkPrimaryTransparent2
            )
        ) {
            Text(
                text = title,
                fontSize = FontSize.Bigger,
                modifier = Modifier.padding(8.dp)
            )
        }
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors().copy(
                containerColor = DarkPrimaryTransparent2
            )
        ) {
            Text(
                text = value,
                fontSize = FontSize.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun PageDropdown2(
    currentValue: Int,
    count: Int,
    top: Boolean = false,
    onValueChange: (Int) -> Unit
) {
    var dropDown by remember { mutableStateOf(false) }
    val daysOfWeek = listOf(
        "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"
    )
    val currentSelected = remember { mutableStateOf(0) }
    val currentValueSelected = remember { mutableStateOf("Monday") }

    Card(
        colors = CardDefaults.cardColors().copy(containerColor = DarkPrimary),
    ) {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .clickable { dropDown = true }
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${currentValueSelected.value}",
//                modifier = Modifier.fillMaxWidth()
            )
            Icon(painterResource(
                if (dropDown == top) {
                    MR.images.ic_arrow_drop_down
                } else {
                    MR.images.ic_arrow_drop_up
                }
            ),
                contentDescription = "Dropdown to select page"
            )
        }
        if (dropDown) {
            Popup(
                onDismissRequest = { dropDown = false }
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors().copy(containerColor = DarkPrimaryTransparent2),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        Modifier.wrapContentWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        daysOfWeek.forEachIndexed { index, string ->
                            DropdownItem(string) {
                                currentValueSelected.value = string
                                onValueChange(index)
                                dropDown = false
                            }
                        }
//                        repeat(daysOfWeek.size) {
//                            DropdownItem("${it + 1}") {
//                                onValueChange(it)
//                                dropDown = false
//                            }
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayOfWeekDropdown(
    modifier: Modifier = Modifier,
    label: String = "Select Day",
    onDaySelected: (String) -> Unit = {}
) {
    val daysOfWeek = listOf(
        "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableStateOf(daysOfWeek.first()) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = selectedDay,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            daysOfWeek.forEach { day ->
                DropdownMenuItem(
                    text = { Text(day) },
                    onClick = {
                        selectedDay = day
                        expanded = false
                        onDaySelected(day)
                    }
                )
            }
        }
    }
}