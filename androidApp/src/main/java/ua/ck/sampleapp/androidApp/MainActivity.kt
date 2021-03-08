package ua.ck.sampleapp.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import ua.ck.sampleapp.shared.Greeting
import ua.ck.sampleapp.shared.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger().log("MainActivity", "onCreate")

        setContent { GreetingView(title = Greeting().greeting()) }
    }
}
