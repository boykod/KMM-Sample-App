package ua.ck.sampleapp.shared.data.repository

import ua.ck.sampleapp.shared.data.base.BaseRepository
import ua.ck.sampleapp.shared.network.api.UserApi
import ua.ck.sampleapp.shared.network.entities.GithubUserResponse


class UserRepository(private val userApi: UserApi) : BaseRepository() {

    suspend fun getUser(userName: String): GithubUserResponse {
        return userApi.getUser(userName)
    }

}