package ua.ck.sampleapp.shared.store.user

import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect
import oolong.effect.none
import org.kodein.di.instance
import ua.ck.sampleapp.shared.data.repository.UserRepository
import ua.ck.sampleapp.shared.di.DiInjection
import ua.ck.sampleapp.shared.network.entities.GithubUserResponse

object UserStore {

    private val userRepository = DiInjection.di.instance<UserRepository>()

    data class Model(
        val data: UserFetchResult = UserFetchResult.Loading
    )

    sealed class Msg {
        data class LoadDataRequest(val username: String) : Msg()
        data class LoadDataSuccess(val user: GithubUserResponse) : Msg()
        data class LoadDataError(val message: String) : Msg()
    }

    private fun loadData(username: String) = effect<Msg> { dispatch ->
        dispatch(Msg.LoadDataSuccess(userRepository.getUser(username)))
    }

    class Props(
        val getUser: (String) -> Msg,
        val data: UserFetchResult
    )

    val init: Init<Model, Msg> = {
        Model() to loadData("JakeWharton")
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            is Msg.LoadDataRequest -> {
                model.copy(data = UserFetchResult.Loading) to loadData(msg.username)
            }
            is Msg.LoadDataError -> {
                model.copy(data = UserFetchResult.Error(msg.message)) to none()
            }
            is Msg.LoadDataSuccess -> {
                model.copy(data = UserFetchResult.Content(msg.user)) to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            { username -> Msg.LoadDataRequest(username) },
            model.data
        )
    }

}