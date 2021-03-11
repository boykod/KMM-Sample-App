package ua.ck.sampleapp.shared.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.instance
import ua.ck.sampleapp.shared.data.base.BaseRepository
import ua.ck.sampleapp.shared.di.DiInjection
import ua.ck.sampleapp.shared.network.api.UserApi
import ua.ck.sampleapp.shared.view.UserView


class UserRepository : BaseRepository() {

    lateinit var userView: UserView

    private val userApi = DiInjection.di.instance<UserApi>()

    fun getUser(userName: String) = launch {
        val userData = userApi.getUser(userName)

        withContext(Dispatchers.Main) {
            userView.updateUserData(userData)
        }
    }

}