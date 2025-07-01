package id.neotica.ui.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.painterResource
import id.neotica.rickpository.res.MR
import id.neotica.ui.shared.navigation.navigateBack
import id.neotica.ui.shared.theme.DarkPrimaryTransparent80
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(
    topBarTitle: String,
    enableActionBar: Boolean = false,
    customActionBar: (() -> Unit)? = null,
    navController: NavController? = null,
    paddingValues: PaddingValues = PaddingValues(),
    paddingValuesBottomOnly: Boolean = true,
    fullScreen: Boolean = false,
    isLoading: Boolean = false,
    enableEye: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    trailingIcon: (@Composable () -> Unit)? = null,
    topBarVisibility: Boolean = true,
    topBarBackground: Boolean? = null,
    topBarModifier: Modifier = Modifier,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            AnimatedVisibility(
                visible = topBarVisibility,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                val topBarColor = if (topBarBackground == true) DarkPrimaryTransparent80 else DarkPrimaryTransparent80

                if (isLoading) LoadingIndicator()

                TopAppBar(
                    title = {
                        Text(topBarTitle)
                    },
                    modifier = topBarModifier,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (topBarBackground!= null) topBarColor else Transparent,
                        titleContentColor = White,
//                        scrolledContainerColor = Transparent
                    ),
                    navigationIcon = {
                        if (enableActionBar) {
                            IconButton(onClick = {
                                if (customActionBar != null) {
                                    customActionBar.invoke()
                                } else {
                                    navController?.navigateBack()
                                }
                            }
                            ) {
                                Icon(
                                    painter = painterResource(MR.images.ic_back),
                                    contentDescription = ""
                                )
                            }
                        }
                    },
                    actions = {
                        val eyeState = remember { mutableStateOf(false) }
                        val blinkCount = remember { mutableIntStateOf(0) }

                        if (trailingIcon != null) {
                            trailingIcon()
                        }

                        if (enableEye) {
                            LaunchedEffect(eyeState.value) {
                                if (eyeState.value) {
                                    val randomDelay = listOf(1000L, 2000L, 3000L, 500L, 5000L, 10000L).random()
                                    delay(randomDelay)
                                    eyeState.value = false
                                }
                            }
                            if (blinkCount.intValue >= 100) {
//                                Toast.makeText(LocalContext.current, "Stop that.", Toast.LENGTH_SHORT).show()
                                blinkCount.intValue = 0
                            }
                            if (!eyeState.value) {
                                IconButton(
                                    onClick = {
                                        val randomIntVal = listOf(
                                            1, 1, 1, 1, 1, 1, 1, 1, 1,
                                            0, 2, 3, 0, 5, 6, 7, 8, 0,
                                            10, 20,
                                        ).random()
                                        eyeState.value = !eyeState.value
                                        blinkCount.intValue += randomIntVal
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(MR.images.ic_eye_open),
                                        contentDescription = "",
                                        tint = White
                                    )
                                }
                            } else {
                                IconButton(
                                    onClick = {eyeState.value = !eyeState.value}
                                ) {
                                    Icon(
                                        painter = painterResource(MR.images.ic_eye_closed),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }
                )
            }
        },
    ) {
        if (!fullScreen) {
            Column(
                modifier = Modifier
                    .padding(
                        if (paddingValuesBottomOnly) {
                            PaddingValues(
                                bottom = paddingValues.calculateBottomPadding()
                            )
                        } else {
                            paddingValues
                        }
                    )
                    .fillMaxSize(),
                horizontalAlignment = horizontalAlignment
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = horizontalAlignment
                ) {
                    content(it)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = horizontalAlignment
            ) { content(it) }
        }
    }
}