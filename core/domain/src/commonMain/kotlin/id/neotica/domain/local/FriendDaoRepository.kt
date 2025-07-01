package id.neotica.domain.local

import id.neotica.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FriendDaoRepository {
    fun getFriends(): Flow<List<User>>
    suspend fun addFriend(friend: User)
    suspend fun unfriend(friend: String)
    suspend fun addFriendList(friends: List<User>)
    suspend fun clearFriends()
}