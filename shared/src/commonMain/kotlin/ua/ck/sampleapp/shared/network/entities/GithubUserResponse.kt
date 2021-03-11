package ua.ck.sampleapp.shared.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GithubUserResponse(
    val login: String,
    @SerialName(value = "html_url")
    val url: String,
    @SerialName(value = "avatar_url")
    val avatar: String,
    val name: String,
    val email: String?,
    val followers: Int,
    val following: Int,
    val location: String,
    @SerialName(value = "public_repos")
    val publicRepos: Int,
    @SerialName(value = "public_gists")
    val publicGists: Int,

)