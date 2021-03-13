package ua.ck.sampleapp.shared

import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect
import oolong.effect.none

object PlatformStore {

    data class Model(
        val platform: String = ""
    )

    sealed class Msg {
        object GetPlatform : Msg()
        data class LoadPlatform(val platform: String) : Msg()
    }

    class Props(
        val getPlatform: () -> Msg,
        val platform: String
    )

    private val loadData = effect<Msg> { dispatch ->
        dispatch(Msg.LoadPlatform(platform = "${Greeting().greeting()} from Oolong"))
    }

    val init: Init<Model, Msg> = {
        Model() to loadData
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            is Msg.GetPlatform -> model to loadData
            is Msg.LoadPlatform -> model.copy(platform = msg.platform) to none()
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            { Msg.GetPlatform },
            model.platform
        )
    }
}