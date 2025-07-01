package id.neotica.ui.shared.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute

private val NavController.canGoBack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavController.navigateBack() {
    if (canGoBack) {
        popBackStack()
    }
}

fun NavController.refreshOnBack() {
    this.previousBackStackEntry?.savedStateHandle?.set("refresh", true)
}

fun NavController.onRefresh(block: () -> Unit) {
    val refresh = this.currentBackStackEntry?.savedStateHandle?.get<Boolean>("refresh") == true
    if (refresh) {
        block()
        this.currentBackStackEntry?.savedStateHandle["refresh"] = false
    }
}

fun NavBackStackEntry?.showExcept(excludedRoutes: Set<Any>): Boolean = excludedRoutes.none {
    if (this?.destination?.route == null) true else this.destination.hasRoute(it::class) == true
}
