package ua.ck.sampleapp.shared

import android.util.Log


actual class Logger actual constructor() {
    actual fun log(tag: String?, message: String) {
        Log.i(tag, message)
    }
}