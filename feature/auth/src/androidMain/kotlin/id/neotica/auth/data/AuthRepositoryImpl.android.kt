package id.neotica.auth.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import id.neotica.domain.ApiResult
import id.neotica.domain.model.User
import kotlinx.coroutines.tasks.await
import dev.gitlive.firebase.auth.FirebaseAuth as FirebaseAuthKmp

private var auth = FirebaseAuth.getInstance()
private val firestore: FirebaseFirestore
    get() = FirebaseFirestore.getInstance()
private val firebase = Firebase.database

actual suspend fun loginResult(
    email: String,
    password: String,
    authKmp: FirebaseAuthKmp
): ApiResult<String> {
    try {
        val loginResult = auth.signInWithEmailAndPassword(email, password).await()
        val user = loginResult.user

        return if (user != null) {
            ApiResult.Success("Login successful")
        } else {
            ApiResult.Error("Login failed: User not found.")
        }
    } catch (e: FirebaseAuthInvalidUserException) {
        return ApiResult.Error("Invalid user: ${e.message}")
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        return ApiResult.Error("Invalid credentials: ${e.message}")
    } catch (e: FirebaseAuthException) {
        return ApiResult.Error("Authentication error: ${e.message}")
    } catch (e: Exception) {
        return ApiResult.Error("Unexpected error: ${e.message}")
    }
}

actual suspend fun registerResult(
    username: String,
    email: String,
    password: String,
    authKmp: FirebaseAuthKmp
): ApiResult<String> {
    try {
        val registerResult = auth.createUserWithEmailAndPassword(email, password).await()
        if (registerResult.user != null) {
            val currentUser = auth.currentUser!!
            val dataUser = User(
                userId = currentUser.uid,
                username = username,
                userEmail = email
            )

            // Save user data in Firestore

            firestore.collection("Users").document(currentUser.uid).set(dataUser).await()

            return ApiResult.Success("Registration successful")
        } else {
            return ApiResult.Error("Registration failed: Unknown error")
        }
    } catch (e: FirebaseAuthException) {
        return ApiResult.Error("Authentication Error: ${e.message}")
    } catch (e: FirebaseFirestoreException) {
        return ApiResult.Error("Firestore Error: ${e.message}")
    } catch (e: Exception) {
        return ApiResult.Error("Unexpected Error: ${e.message}")
    }
}

actual suspend fun isUsernameExistResult(username: String, authKmp: FirebaseAuthKmp): ApiResult<Boolean> {
    return try {
        val snapshot = firestore.collection("Users").get().await()
        val userList = snapshot.documents.mapNotNull {
            it.toObject(User::class.java)
        }
        val checkUser = userList.filter { it.username == username }
        val isUserExist = checkUser[0].username == username
        ApiResult.Success(isUserExist)
    } catch (e: Exception) {
        ApiResult.Success(false)
    }
}

actual fun deleteAccountResult(authKmp: FirebaseAuthKmp) {
    val currentUser = auth.currentUser
    currentUser!!.delete().addOnCompleteListener {
        Log.d("deleteuser", "usr deleted")
    }
    firestore.collection("Users").document(currentUser.uid).delete()

    val reference = firebase.getReference("userdata").child(currentUser.uid)
    reference.removeValue()
}
actual fun logoutResult(authKmp: FirebaseAuthKmp) {
    auth.signOut()
}
actual fun resetPasswordResult(email: String, authKmp: FirebaseAuthKmp) {
    auth.sendPasswordResetEmail(email)
}
actual fun sendEmailVerificationResult(authKmp: FirebaseAuthKmp) {
    auth.currentUser?.sendEmailVerification()
}

fun isLoggedIn(): Boolean {
    auth = FirebaseAuth.getInstance()
    return auth.currentUser != null
}

actual fun isUserVerifiedResult(authKmp: FirebaseAuthKmp): Boolean {
    auth = FirebaseAuth.getInstance()

    val verified = auth.currentUser?.isEmailVerified
    if (!isLoggedIn()) return false
    return verified!!
}

actual suspend fun getCurrentUserTokenResult(authKmp: FirebaseAuthKmp): String {
    auth = FirebaseAuth.getInstance()
    return auth.currentUser?.getIdToken(true)?.await()?.token ?: ""
}

actual fun isLoggedInResult(authKmp: FirebaseAuthKmp): Boolean {
    auth = FirebaseAuth.getInstance()
    return auth.currentUser != null
}