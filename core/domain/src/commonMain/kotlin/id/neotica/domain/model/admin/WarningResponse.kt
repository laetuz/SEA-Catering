package id.neotica.domain.model.admin

import kotlinx.serialization.Serializable

@Serializable
data class WarningResponse(
    val id: String,
    val bookId: String,
    val bookTitle: String,
    val isAppealed: Boolean,
    val isApproved: Boolean,
    val message: String,
    val version: Int,
    val createdAt: Long
)
