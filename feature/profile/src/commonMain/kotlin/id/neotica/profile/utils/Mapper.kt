package id.neotica.profile.utils

import id.neotica.domain.NeoUser
import id.neotica.domain.model.User
import id.neotica.profile.data.local.ProfileEntity
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun ProfileEntity.toUser(): NeoUser {
    return NeoUser(
        id = id,
        firebaseId = firebaseId,
        username = username,
        email = email,
        displayName = displayName,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}

fun NeoUser.toEntity(): ProfileEntity =
    ProfileEntity(
        id = id,
        firebaseId = firebaseId,
        username = username,
        email = email,
        displayName = displayName,
        imageUrl = imageUrl,
        createdAt = createdAt
    )

@OptIn(ExperimentalTime::class)
fun User.toNeoUser(): NeoUser {
    return NeoUser(
        firebaseId = userId,
        username = username,
        email = userEmail,
        imageUrl = imageUrl,
        createdAt = Clock.System.now().toEpochMilliseconds()
    )
}