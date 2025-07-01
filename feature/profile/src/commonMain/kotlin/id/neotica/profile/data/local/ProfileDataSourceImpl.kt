package id.neotica.profile.data.local

import co.touchlab.kermit.Logger

class ProfileDataSourceImpl(
    private val dao: ProfileDao
) {
    suspend fun getProfile(firebaseId: String): ProfileEntity? {
        return dao.getProfile(firebaseId)
    }

    suspend fun insertProfile(profile: ProfileEntity) {
        Logger.d("ProfileDataSourceImpl") { "GetProfile inserted" }
        return dao.insertProfile(profile)
    }
}