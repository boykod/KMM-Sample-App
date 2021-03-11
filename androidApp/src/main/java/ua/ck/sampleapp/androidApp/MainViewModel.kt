package ua.ck.sampleapp.androidApp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.sampleapp.shared.view.UserView
import ua.ck.sampleapp.shared.data.repository.UserRepository
import ua.ck.sampleapp.shared.network.entities.GithubUserResponse

class MainViewModel : ViewModel(), UserView {

    private val userRepository = UserRepository().apply {
        userView = this@MainViewModel
    }

    val userData = MutableLiveData<GithubUserResponse>()

    init {
        userRepository.getUser("JakeWharton")
    }

    fun getUserByUsername(username: String) {
        userRepository.getUser(username)
    }

    override fun updateUserData(data: GithubUserResponse) {
        userData.value = data
    }

}