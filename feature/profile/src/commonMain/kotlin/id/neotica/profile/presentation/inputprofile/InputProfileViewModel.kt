package id.neotica.profile.presentation.inputprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import id.neotica.domain.ApiResult
import id.neotica.domain.NeoUser
import id.neotica.profile.domain.ProfileRepository
import id.neotica.routes.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class InputProfileViewModel(
    private val profileRepo: ProfileRepository,
): ViewModel() {

    private var _profile: MutableStateFlow<NeoUser?> = MutableStateFlow(null)
    val profile = _profile.asStateFlow()

    private var _profileChannel = Channel<ProfileState>()
    val profileChannel = _profileChannel.receiveAsFlow()

//    fun fillProfile(user: NeoUser) = viewModelScope.launch {
//        val fill = profileRepo.fillProfile(user)
//
//        fill.collect {
//            when (it) {
//                is ApiResult.Loading -> {}
//                is ApiResult.Success -> {
//                    _profile.value = it.data
//                    _profileChannel.send(
//                        ProfileState.OnNavigate(Screen.ProfileScreen)
//                    )
//                }
//                is ApiResult.Error -> {
//                    Logger.e("InputProfileViewModel") { it.errorMessage.toString() }
//                    _profileChannel.send(
//                        ProfileState.OnError(
//                            message = it.errorMessage.toString(),
//                        ),
//                    )
//                }
//            }
//        }
//    }

    sealed interface ProfileState {
        data class OnError(val message: String): ProfileState
        data class OnNavigate(val route: Screen?): ProfileState
    }
}