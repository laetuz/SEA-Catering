package id.neotica.profile.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import id.neotica.profile.data.ProfileRepositoryImpl
import id.neotica.profile.domain.ProfileRepository
import id.neotica.profile.presentation.ProfileViewModel
import id.neotica.profile.presentation.inputprofile.InputProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModules = module {
    single<FirebaseFirestore>{ Firebase.firestore }
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
    viewModelOf(::ProfileViewModel)
    viewModelOf(::InputProfileViewModel)
}