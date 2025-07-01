package id.neotica.profile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    val id: String,
    val firebaseId: String,
    val username: String,
    val email: String,
    val displayName: String,
    val imageUrl: String,
    val createdAt: Long
)