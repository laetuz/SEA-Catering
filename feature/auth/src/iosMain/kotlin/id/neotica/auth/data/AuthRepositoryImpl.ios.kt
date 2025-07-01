package id.neotica.auth.data

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import id.neotica.domain.ApiResult

actual suspend fun loginResult(
    email: String,
    password: String,
    authKmp: FirebaseAuth
): ApiResult<String> {
    try {
        val loginResult = authKmp.signInWithEmailAndPassword(email, password)
        val user = loginResult.user

        return if (user != null) {
            if (user.isEmailVerified) {
                ApiResult.Success("Login successful")
            } else {
                ApiResult.Error("Email not verified. Please verify your email.")
            }
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
    authKmp: FirebaseAuth
): ApiResult<String> {
    TODO("Not yet implemented")
}

actual suspend fun isUsernameExistResult(username: String, authKmp: FirebaseAuth): ApiResult<Boolean> {
    TODO("Not yet implemented")
}

actual fun deleteAccountResult(authKmp: FirebaseAuth) {}
actual fun logoutResult(authKmp: FirebaseAuth) {}
actual fun resetPasswordResult(email: String, authKmp: FirebaseAuth) {}
actual fun sendEmailVerificationResult(authKmp: FirebaseAuth) {}
actual fun isUserVerifiedResult(authKmp: FirebaseAuth): Boolean {
    TODO("Not yet implemented")
}

actual suspend fun getCurrentUserTokenResult(authKmp: FirebaseAuth): String {
    return authKmp.currentUser?.getIdTokenResult(true)?.token?: ""
}

actual fun isLoggedInResult(authKmp: FirebaseAuth): Boolean {
    return authKmp.currentUser != null
}