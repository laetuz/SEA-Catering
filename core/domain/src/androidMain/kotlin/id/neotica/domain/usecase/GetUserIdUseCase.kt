package id.neotica.domain.usecase

import id.neotica.domain.remote.UserRepository

class GetUserIdUseCase(private val userRepo: UserRepository) {
    operator fun invoke(): String {
        return if (userRepo.isLoggedIn()) {
            userRepo.getUserId()
        } else {
            ""
        }
    }
}