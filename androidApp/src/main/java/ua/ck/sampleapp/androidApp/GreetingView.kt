package ua.ck.sampleapp.androidApp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import oolong.Dispatch
import ua.ck.sampleapp.shared.PlatformStore

@Composable
fun GreetingView(props: PlatformStore.Props, dispatch: Dispatch<PlatformStore.Msg>) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = props.platform)
    }
}