package ua.ck.sampleapp.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import oolong.Render
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import ua.ck.sampleapp.shared.store.user.UserStore
import kotlin.coroutines.CoroutineContext

fun PlatformStore.runtime(render: Render<PlatformStore.Msg, PlatformStore.Props>) =
    oolong.runtime(
        init,
        update,
        view,
        render,
        MainLoopDispatcher,
        MainLoopDispatcher,
        MainLoopDispatcher
    )

fun UserStore.runtime(render: Render<UserStore.Msg, UserStore.Props>) =
    oolong.runtime(
        init,
        update,
        view,
        render,
        MainLoopDispatcher,
        MainLoopDispatcher,
        MainLoopDispatcher
    )

private object MainLoopDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            block.run()
        }
    }
}