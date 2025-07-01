package id.neotica.auth.domain

import id.neotica.domain.ApiResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<String>
    suspend fun register(username: String, email: String, password: String): ApiResult<String>
    suspend fun isUsernameExist(username: String): ApiResult<Boolean>
    fun deleteAccount()
    fun logout()
    fun resetPassword(email: String)
    fun sendEmailVerification()
    fun isUserVerified(): Boolean
    suspend fun getCurrentUserToken(): String
    fun isLoggedIn(): Boolean
    fun getUserId(): String?
}