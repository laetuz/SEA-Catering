package id.neotica.auth.domain

import id.neotica.domain.ApiResult
import id.neotica.domain.model.User
import kotlinx.coroutines.flow.Flow

interface NeoUserRepository {
    fun createUser(user: User): Flow<ApiResult<User>>
    fun getUserDetail(userId: String): Flow<ApiResult<User>>
    fun getUserDetailByFirebase(userId: String): Flow<ApiResult<User>>
}