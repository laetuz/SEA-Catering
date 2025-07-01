package id.neotica.seacatering.di

import id.neotica.auth.di.authModules
import id.neotica.ktor.ktorModule
import id.neotica.seacatering.screen.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
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
   singleOf(::HomeViewModel)
}