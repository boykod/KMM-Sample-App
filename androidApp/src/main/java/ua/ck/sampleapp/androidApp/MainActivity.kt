package ua.ck.sampleapp.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import ua.ck.sampleapp.shared.Logger
import ua.ck.sampleapp.shared.store.user.UserStore.init
import ua.ck.sampleapp.shared.store.user.UserStore.update
import ua.ck.sampleapp.shared.store.user.UserStore.view


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger().log("MainActivity", "onCreate")

        oolong.runtime(
            init,
            update,
            view,
            { props, dispatch ->
                setContent { GreetingView(props, dispatch) }
            }
        )
    }
}
