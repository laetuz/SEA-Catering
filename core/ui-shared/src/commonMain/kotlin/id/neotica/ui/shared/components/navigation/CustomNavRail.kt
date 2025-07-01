package id.neotica.ui.shared.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import id.neotica.ui.shared.theme.DarkPrimaryTransparent2

@Composable
fun CustomNavRail(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        color = DarkPrimaryTransparent2,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
            .padding(
                start = 12.dp, end = 12.dp
            ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(
                    bottom = 0.dp, top = 18.dp
                )
                .windowInsetsPadding(windowInsets)
                .widthIn(min = 80.dp)
                .selectableGroup(),
            verticalArrangement = Arrangement.Center,
            content = content
        )
    }
}