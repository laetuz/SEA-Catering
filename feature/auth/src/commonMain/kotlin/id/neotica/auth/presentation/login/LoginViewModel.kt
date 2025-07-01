package id.neotica.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.neotica.auth.domain.AuthRepository
import id.neotica.domain.ApiResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: AuthRepository,
): ViewModel() {

    private var _loginResult = MutableSharedFlow<String>()
    val loginResult = _loginResult.asSharedFlow()

    private var _loading = MutableSharedFlow<Boolean>()
    val loading get() = _loading.asSharedFlow()

    fun login(email: String, password: String, callback: () -> Unit) = viewModelScope.launch {
        _loading.emit(true)
        val result = authRepo.login(email, password)
        when (result) {
            is ApiResult.Loading -> _loading.emit(true)
            is ApiResult.Success -> {
                _loading.emit(false)
                _loginResult.emit(result.data.toString())
                callback()
            }
            is ApiResult.Error -> {
                _loading.emit(false)
                _loginResult.emit(result.errorMessage.toString())
            }
        }
    }
}