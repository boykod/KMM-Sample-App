package ua.ck.sampleapp.shared.data.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.ck.sampleapp.shared.Logger


abstract class BaseRepository {

    val networkScope = CoroutineScope(Dispatchers.Default)

    internal fun executeNetworkOrDbAction(action: suspend () -> Unit) {
        networkScope.launch {
            try {
                action.invoke()
            } catch (throwable: Throwable) {
                Logger().log(message = "failed")
            }
        }
    }

}