package ua.ck.sampleapp.shared.data.repository

import org.kodein.di.instance
import ua.ck.sampleapp.shared.data.base.BaseRepository
import ua.ck.sampleapp.shared.di.DiInjection
import ua.ck.sampleapp.shared.network.api.UserApi


class UserRepository : BaseRepository() {

    private val userApi = DiInjection.di.instance<UserApi>()

    fun getUser(userName: String) {
        executeNetworkOrDbAction { userApi.getUser(userName) }
    }

}