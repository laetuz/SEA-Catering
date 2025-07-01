package id.neotica.ui.shared.components.scroll

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun scrollTopValue(listState: LazyListState): Boolean {
    /**This is the state to make the top app bar disappear/appears on lazyList scroll**/
    var isScrollingUp by remember { mutableStateOf(true) }
    var previousOffset by remember { mutableIntStateOf(0) }
    val scrollThreshold = 100

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                if (kotlin.math.abs(currentOffset - previousOffset) > scrollThreshold) {
                    isScrollingUp = currentOffset < previousOffset
                    previousOffset = currentOffset
                }
            }
    }
    return isScrollingUp
}

@Composable
fun scrollBottomValue(listState: LazyListState): Boolean {
    /**This is the state to make the top app bar disappear/appears on lazyList scroll**/
    var isScrollingUp by remember { mutableStateOf(true) }
    var previousOffset by remember { mutableIntStateOf(0) }
    val scrollThreshold = 0

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.offset ?: 0 }
            .collect { currentOffset ->
                if (kotlin.math.abs(currentOffset - previousOffset) > scrollThreshold) {
                    isScrollingUp = currentOffset < previousOffset
                    previousOffset = currentOffset
                }
            }
    }
    return isScrollingUp
}