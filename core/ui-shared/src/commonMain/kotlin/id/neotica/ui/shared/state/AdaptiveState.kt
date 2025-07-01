package id.neotica.ui.shared.state

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass

sealed class AdaptiveState {
    data object Compact: AdaptiveState()
    data object Medium: AdaptiveState()
    data object Expanded: AdaptiveState()

    companion object {
        fun from(adaptiveInfo: WindowAdaptiveInfo): AdaptiveState {
            return when (adaptiveInfo.windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> Compact
                WindowWidthSizeClass.MEDIUM -> Medium
                WindowWidthSizeClass.EXPANDED -> Expanded
                else -> Compact
            }
        }
    }
}