package ua.ck.sampleapp.shared.data.base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.ck.sampleapp.shared.Logger


abstract class BaseRepository {

    fun launch(block: suspend CoroutineScope.() -> Unit) =
        GlobalScope.launch(context = exceptionHandler, block = block)

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    private fun handleError(throwable: Throwable) {
        Logger().log(message = "Something went wrong: $throwable")
    }

}