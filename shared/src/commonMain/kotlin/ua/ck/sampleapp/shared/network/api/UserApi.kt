package ua.ck.sampleapp.shared.network.api

import io.ktor.client.*
import io.ktor.client.request.*
import ua.ck.sampleapp.shared.network.entities.GithubUserResponse

class UserApi(private val client: HttpClient) {

    suspend fun getUser(userName: String): GithubUserResponse {
        return client.get("$users$userName")
    }

}