package id.neotica.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var userId: String = "",
    val username: String = "",
    val userEmail: String = "",
    val status: String = "default",
    val imageUrl: String = "https://www.pngarts.com/files/6/User-Avatar-in-Suit-PNG.png"
)