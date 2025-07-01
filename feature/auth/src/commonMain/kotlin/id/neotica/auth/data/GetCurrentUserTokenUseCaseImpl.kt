package id.neotica.auth.data

import id.neotica.auth.domain.AuthRepository
import id.neotica.auth.domain.GetCurrentUserTokenUseCase

class GetCurrentUserTokenUseCaseImpl(
    private val authRepo: AuthRepository
): GetCurrentUserTokenUseCase {
    override suspend fun invoke() = authRepo.getCurrentUserToken()
}