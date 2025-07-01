package id.neotica.domain.remote

import id.neotica.domain.ApiResult
import kotlinx.coroutines.flow.Flow

interface SuperAdminRepository {
    suspend fun postMinimumVersion(version: Int)
    suspend fun postAuras(aura: Long)
    suspend fun getMinimumVersion(): Flow<ApiResult<Int>>
}