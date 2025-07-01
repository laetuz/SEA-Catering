package id.neotica.auth.data

import id.neotica.auth.domain.NeoUserRepository
import id.neotica.domain.ApiResult
import id.neotica.domain.model.User
import id.neotica.ktor.utils.safeApiCall
import id.neotica.ktor.utils.safeApiCallNew
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

class NeoUserRepositoryImpl(private val client: HttpClient) {
//    override fun createUser(user: User): Flow<ApiResult<User>> = safeApiCallNew<User> {
//        client.post("$baseUrl/user") {
//            headers.append("Content-Type", "application/json")
//            setBody(user)
//        }
//    }
//
//    override fun getUserDetail(userId: String): Flow<ApiResult<User>> = safeApiCall {
//        client.get("${baseUrl}/user/$userId").body()
//    }
//
//    override fun getUserDetailByFirebase(userId: String): Flow<ApiResult<User>> = safeApiCall {
//        client.get("${baseUrl}/user/firebase/$userId").body()
//    }
}