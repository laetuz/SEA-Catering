package id.neotica.ui.shared.components.scroll

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.util.lerp

@Composable
fun nestedScrollConnection(
    headerAlpha: MutableState<Float>,
    headerSize: MutableState<Dp> = mutableStateOf(2.dp),
    headerMinSize: Dp = 4.dp,
    headerMaxSize: Dp = 100.dp,
    headerMinAlpha: Float = 0f,
    headerMaxAlpha: Float = 1f,
): NestedScrollConnection {
    val density = LocalDensity.current

    // Only need to find tabsHeight if it is not fixed
    var tabsSize by remember { mutableStateOf(IntSize(0, 0)) }
    val tabsHeight by remember(tabsSize) { mutableStateOf(with(density) { tabsSize.height.toDp() }) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                fun deltaDp() = with(density) { available.y.toDp() }
                // Handle scroll up
                if (available.y < 0 && headerSize.value > headerMinSize) {
                    headerSize.value = max(headerMinSize, headerSize.value + deltaDp())
                }
                // Handle scroll down
                if (available.y > 0 && headerSize.value < headerMaxSize) {
                    headerSize.value = min(headerMaxSize, headerSize.value + deltaDp())
                }
                val progress = (headerSize.value - headerMinSize) / (headerMaxSize - headerMinSize)
                headerAlpha.value = lerp(headerMinAlpha, headerMaxAlpha, progress)

                return Offset.Zero
            }
        }
    }

    return nestedScrollConnection
}