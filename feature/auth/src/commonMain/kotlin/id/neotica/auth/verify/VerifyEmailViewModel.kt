package id.neotica.auth.verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.neotica.auth.domain.AuthRepository
import id.neotica.domain.local.CurrentUserRepository
import id.neotica.domain.local.FriendDaoRepository
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
    private val authRepo: AuthRepository,
    private val friendDatabase: FriendDaoRepository,
    private val currentUserDatabase: CurrentUserRepository
): ViewModel() {

    fun isUserVerified() = authRepo.isUserVerified()
    fun sendEmailVerification() = authRepo.sendEmailVerification()
    fun logout() = viewModelScope.launch {
        currentUserDatabase.clearCurrent()
        friendDatabase.clearFriends()
        authRepo.logout()
    }
}