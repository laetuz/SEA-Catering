package id.neotica.domain.model.error

import kotlinx.serialization.Serializable

@Serializable
data class NotFoundResponse(
    val error: String
)
