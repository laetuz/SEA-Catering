package id.neotica.seacatering.screen.home

import androidx.lifecycle.ViewModel
import id.neotica.auth.data.AuthRepositoryImpl
import id.neotica.auth.domain.AuthRepository

class HomeViewModel(
    private val authRepo: AuthRepository
): ViewModel() {
    fun isLoggedIn(): Boolean = authRepo.isLoggedIn()
}