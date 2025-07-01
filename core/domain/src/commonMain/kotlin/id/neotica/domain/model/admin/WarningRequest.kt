package id.neotica.domain.model.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WarningRequest(
    val bookId: String? = null,
    val message: String? = null,
    @SerialName("isAppealed")
    val isAppealed: Boolean,
    @SerialName("isApproved")
    val isApproved: Boolean,
    val version: Int? = null
)