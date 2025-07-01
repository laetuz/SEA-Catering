package id.neotica.ui.shared.state

import kotlinx.serialization.Serializable

sealed class ScreenState {
    @Serializable
    data object User: ScreenState()
    @Serializable
    data object Global: ScreenState()
    @Serializable
    data object UserInventory: ScreenState()
    @Serializable
    data object GlobalItem: ScreenState()
    @Serializable
    data object InventoryItem: ScreenState()
}