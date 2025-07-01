package id.neotica.auth.domain

interface GetCurrentUserTokenUseCase {
    suspend fun invoke(): String
}