package id.neotica.domain

import kotlinx.serialization.Serializable

@Serializable
data class NeoUser(
    val id: String = "",
    val firebaseId: String,
    val username: String,
    val email: String,
    val displayName: String = "",
    val imageUrl: String = "https://www.pngarts.com/files/6/User-Avatar-in-Suit-PNG.png",
    val createdAt: Long
)
