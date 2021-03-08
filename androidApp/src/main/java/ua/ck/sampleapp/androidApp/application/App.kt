package ua.ck.sampleapp.androidApp.application

import android.app.Application
import ua.ck.sampleapp.shared.di.DiInjection


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DiInjection.init()
    }
}