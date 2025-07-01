package id.neotica.auth.data

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import id.neotica.auth.domain.AuthRepository
import id.neotica.domain.ApiResult
import id.neotica.domain.NeoUser
import io.ktor.client.HttpClient
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class AuthRepositoryImpl(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    private val client: HttpClient,
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String,
    ): ApiResult<String> = loginResult(email, password, auth)

    @OptIn(ExperimentalTime::class)
    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): ApiResult<String> {
        return try {
            val registerResult = auth.createUserWithEmailAndPassword(email, password)
            val currentUser = auth.currentUser ?: return ApiResult.Error("User creation failed")

            currentUser.sendEmailVerification()

            Logger.i { "neouser auth: ${currentUser.uid}" }

            val user = NeoUser(
                firebaseId = currentUser.uid,
                username = username,
                email = email,
                displayName = "",
                createdAt = Clock.System.now().toEpochMilliseconds()
            )

            // ✅ Await this call and handle exceptions
            firestore.collection("users")
                .document(currentUser.uid)
                .set(user)

            ApiResult.Success("Registration successful")
        } catch (e: FirebaseAuthException) {
            Logger.e { "Authentication error: ${e.message}" }
            ApiResult.Error("Authentication Error: ${e.message}")
        } catch (e: FirebaseFirestoreException) {
            Logger.e { "Firestore error: ${e.message}" }
            ApiResult.Error("Firestore Error: ${e.message}")
        } catch (e: Exception) {
            Logger.e { "Unexpected error: ${e.message}" }
            ApiResult.Error("Unexpected Error: ${e.message}")
        }
    }

//    fun saveUser(user: NeoUser) = safeApiCallNew<NeoUser> {
//        client.post("$baseUrl/user") {
//            headers.append("Content-Type", "application/json")
//            setBody(user)
//        }
//    }

    override suspend fun isUsernameExist(username: String): ApiResult<Boolean> = isUsernameExistResult(username, auth)

    override fun deleteAccount() = deleteAccountResult(auth)

    override fun logout() = logoutResult(auth)

    override fun resetPassword(email: String) = resetPasswordResult(email, auth)

    override fun sendEmailVerification() = sendEmailVerificationResult(auth)
    override fun isUserVerified(): Boolean = isUserVerifiedResult(auth)
    override suspend fun getCurrentUserToken(): String = getCurrentUserTokenResult(auth)
    override fun isLoggedIn() = auth.currentUser != null

    override fun getUserId() = auth.currentUser?.uid
}

expect suspend fun loginResult(email: String, password: String, authKmp: FirebaseAuth): ApiResult<String>
expect suspend fun registerResult(
    username: String,
    email: String,
    password: String,
    authKmp: FirebaseAuth
): ApiResult<String>
expect suspend fun isUsernameExistResult(username: String, authKmp: FirebaseAuth): ApiResult<Boolean>
expect fun deleteAccountResult(authKmp: FirebaseAuth)
expect fun logoutResult(authKmp: FirebaseAuth)
expect fun resetPasswordResult(email: String, authKmp: FirebaseAuth)
expect fun sendEmailVerificationResult(authKmp: FirebaseAuth)
expect fun isUserVerifiedResult(authKmp: FirebaseAuth): Boolean
expect suspend fun getCurrentUserTokenResult(authKmp: FirebaseAuth): String
expect fun isLoggedInResult(authKmp: FirebaseAuth): Boolean