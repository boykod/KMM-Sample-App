package ua.ck.sampleapp.shared.view

import ua.ck.sampleapp.shared.network.entities.GithubUserResponse

interface UserView {
    fun updateUserData(data: GithubUserResponse)
}