package ua.ck.sampleapp.shared.utils.logger

import io.ktor.client.features.logging.*


class HttpLogger : Logger {
    override fun log(message: String) {
        ua.ck.sampleapp.shared.Logger().log(message = message)
    }
}