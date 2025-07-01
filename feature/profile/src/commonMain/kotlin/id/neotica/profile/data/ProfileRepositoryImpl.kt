package id.neotica.profile.data

import dev.gitlive.firebase.firestore.FirebaseFirestore
import id.neotica.auth.domain.AuthRepository
import id.neotica.domain.ApiResult
import id.neotica.domain.model.User
import id.neotica.profile.domain.ProfileRepository

class ProfileRepositoryImpl(
    val firestore: FirebaseFirestore,
    private val authRepo: AuthRepository,
): ProfileRepository {

    override suspend fun getUserDetail(): ApiResult<User?> {
        return try {
            if (authRepo.getUserId() != null) {
                val snapshot = firestore.collection("Users").document(authRepo.getUserId().toString()).get()
                if (snapshot.exists) {
                    val userValue = snapshot.data<User>()
                    // Save user data in Firestore
                    ApiResult.Success(userValue)
                } else {
                    ApiResult.Error("null user")
                }

            } else {
                ApiResult.Success(null)
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message)
        }
    }

    override suspend fun getUserProfilePic(): String {
        TODO("Not yet implemented")
    }
//    @OptIn(InternalCoroutinesApi::class)
//    override suspend fun getUserProfilePic(): String = withContext(Dispatchers.IO) {
//        val storageRef = FirebaseStorage.getInstance().reference
//            .child("profilePicture/${getUserId()}")
//
//        suspendCancellableCoroutine { cont ->
//            storageRef.downloadUrl.addOnSuccessListener { uri ->
//                cont.resume(uri.toString())
//            }.addOnFailureListener { exc ->
//                cont.tryResumeWithException(exc)
//            }
//        }
//    }

    override suspend fun changeProfilePicture(uri: String) {
        TODO("Not yet implemented")
    }
//    override suspend fun changeProfilePicture(uri: String) {
//        val storageRef = FirebaseStorage.getInstance().reference
//        storageRef.child("profilePicture/${getUserId()}").putFile(uri.toUri())
//    }

    override suspend fun changeUserProfile(user: User) {
        firestore.collection("Users").document(authRepo.getUserId().toString()).set(user)
    }
}