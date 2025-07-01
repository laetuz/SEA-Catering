package id.neotica.profile.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import id.neotica.auth.domain.AuthRepository
import id.neotica.domain.ApiResult
import id.neotica.domain.NeoUser
import id.neotica.domain.local.CurrentUserRepository
import id.neotica.domain.local.FriendDaoRepository
import id.neotica.domain.model.User
import id.neotica.profile.domain.ProfileRepository
import id.neotica.routes.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepo: AuthRepository,
    private val profileRepo: ProfileRepository,
//    private val friendDatabase: FriendDaoRepository,
//    private val currentUserDatabase: CurrentUserRepository
): ViewModel() {

    private var _user: MutableStateFlow<ApiResult<User?>> = MutableStateFlow(ApiResult.Loading())

    private var _currentUser: MutableStateFlow<ApiResult<User>?> = MutableStateFlow(
        ApiResult.Loading()
    )
    val currentUser = _currentUser.asStateFlow()

    private var _profile: MutableStateFlow<NeoUser?> = MutableStateFlow(null)
    val profile = _profile.asStateFlow()

    private var _profileChannel = Channel<ProfileState>()
    val profileChannel = _profileChannel.receiveAsFlow()

    init {
        getUser()
    }

    fun isLoggedIn(): Boolean {
        return authRepo.isLoggedIn()
    }

    fun logout() = viewModelScope.launch {
        authRepo.logout()
    }

    fun deleteAccount() = viewModelScope.launch {
        authRepo.deleteAccount()
    }

    private fun getUser() = viewModelScope.launch {
        val userNew = profileRepo
            .getUserDetail()
//            .getUserDetailNew(authRepo.getUserId().toString())
        _user.value = userNew
//        userNew.collect {
//            when (it) {
//                is ApiResult.Success -> {
//                    currentUserDatabase.saveNeoUser(it.data!!)
//                    _profile.value = it.data
//                    Logger.d("profileVM") { it.data.toString() }
//                }
//                is ApiResult.Error -> {
//                    Logger.e("profileVM") { it.errorMessage.toString() }
//                    _profileChannel.send(
//                        ProfileState.OnError(
//                            message = it.errorMessage.toString(),
//                        ),
//                    )
//                    _profileChannel.send(
//                        ProfileState.OnNavigate(Screen.InputProfileScreen)
//                    )
//                    _user.value = ApiResult.Error(it.errorMessage.toString())
//                }
//                is ApiResult.Loading -> {}
//            }
//        }
    }

    fun changeProfilePic(uri: String) = CoroutineScope(Dispatchers.IO).launch {
        getUser()
        profileRepo.changeProfilePicture(uri)
    }

    val visiblePermissionQueue = mutableStateListOf<String>()

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted) {
            visiblePermissionQueue.add(0, permission)
        }
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        authRepo.resetPassword(email)
    }

    sealed interface ProfileState {
        data class OnError(val message: String): ProfileState
        data class OnNavigate(val route: Screen?): ProfileState
    }
}