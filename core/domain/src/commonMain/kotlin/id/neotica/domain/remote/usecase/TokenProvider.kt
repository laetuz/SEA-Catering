package id.neotica.domain.remote.usecase

interface TokenProvider {
    suspend fun getToken(): String?
}