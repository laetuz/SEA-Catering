package id.neotica.domain.usecase

import id.neotica.domain.ApiResult
import id.neotica.domain.local.CurrentUserRepository
import id.neotica.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetUserNameUseCase(
    private val userDatabase: CurrentUserRepository
) {
    operator fun invoke(): String {
        return currentUserName()
    }
    private var _userEntity: MutableStateFlow<ApiResult<User?>> = MutableStateFlow(
        ApiResult.Loading())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getUser()
        }
    }

    private fun currentUserName(): String = _userEntity.value.data?.username ?: ""

    private suspend fun getUser() = withContext(Dispatchers.IO){
        val userData = userDatabase.getUser()
        userData.collect {
            if (it.isEmpty()) {
                _userEntity.value = ApiResult.Error("")
            } else {
                _userEntity.value = ApiResult.Success(it[0])
            }
        }
    }
}