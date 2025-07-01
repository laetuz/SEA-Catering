package id.neotica.seacatering.screen.subscription.confirmation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import co.touchlab.kermit.Logger
import id.neotica.routes.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubscriptionDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val args = savedStateHandle.toRoute<Screen.SubscriptionDetailScreen>()

    private var _packageId = MutableStateFlow(0)
    val packageId = _packageId.asStateFlow()

    init {
        Logger.d { "subscription detail view model init: $args" }
        getArgs()
    }

    fun getArgs() = viewModelScope.launch {
        _packageId.value = args.id
        Logger.d { "subscription detail package: ${_packageId.value}" }
    }
}