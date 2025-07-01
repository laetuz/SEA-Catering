package id.neotica.auth.di

import id.neotica.auth.data.AuthRepositoryImpl
import id.neotica.auth.data.GetCurrentUserTokenUseCaseImpl
import id.neotica.auth.data.NeoUserRepositoryImpl
import id.neotica.auth.domain.AuthRepository
import id.neotica.auth.domain.GetCurrentUserTokenUseCase
import id.neotica.auth.domain.NeoUserRepository
import id.neotica.auth.presentation.login.LoginViewModel
import id.neotica.auth.presentation.register.RegisterViewModel
import id.neotica.auth.verify.VerifyEmailViewModel
import id.neotica.firebaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModules = module {
    includes(firebaseModule)
    singleOf(::GetCurrentUserTokenUseCaseImpl).bind<GetCurrentUserTokenUseCase>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
//    singleOf(::NeoUserRepositoryImpl).bind<NeoUserRepository>()
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::VerifyEmailViewModel)
}