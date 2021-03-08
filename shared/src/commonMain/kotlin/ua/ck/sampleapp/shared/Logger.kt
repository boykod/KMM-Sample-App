package ua.ck.sampleapp.shared

expect class Logger() {
    fun log(tag: String? = null, message: String)
}