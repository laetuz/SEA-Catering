package id.neotica.profile.domain

import id.neotica.domain.ApiResult
import id.neotica.domain.NeoUser
import id.neotica.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getUserDetail(): ApiResult<User?>
    suspend fun getUserProfilePic(): String
    suspend fun changeProfilePicture(uri: String)
    suspend fun changeUserProfile(user: User)
}