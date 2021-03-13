package ua.ck.sampleapp.shared.store.user

import ua.ck.sampleapp.shared.network.entities.GithubUserResponse

sealed class UserFetchResult {
    object Empty : UserFetchResult()
    object Loading : UserFetchResult()
    data class Error(val message: String) : UserFetchResult()
    data class Content(val data: GithubUserResponse) : UserFetchResult()
}