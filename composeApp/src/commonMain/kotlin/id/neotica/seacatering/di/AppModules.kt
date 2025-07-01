package id.neotica.seacatering.di

import id.neotica.auth.di.authModules
import id.neotica.ktor.ktorModule
import id.neotica.profile.di.profileModules
import id.neotica.seacatering.screen.home.HomeViewModel
import id.neotica.seacatering.screen.subscription.checkout.SubscriptionPaymentViewModel
import id.neotica.seacatering.screen.subscription.detail.SubscriptionDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            viewModelModules,
            ktorModule
        )
    }
}

val viewModelModules = module {
    includes(authModules)
    includes(profileModules)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SubscriptionDetailViewModel)
    viewModelOf(::SubscriptionPaymentViewModel)
}