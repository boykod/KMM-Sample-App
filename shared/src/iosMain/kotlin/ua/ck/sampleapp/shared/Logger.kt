package ua.ck.sampleapp.shared

import platform.Foundation.NSLog


actual class Logger actual constructor() {
    actual fun log(tag: String?, message: String) = NSLog(message)
}