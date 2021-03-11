package ua.ck.sampleapp.androidApp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.ck.sampleapp.shared.Greeting

@Composable
fun GreetingView(viewModel: MainViewModel) {
    val data by viewModel.userData.observeAsState("title_userdata")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        Text(text = Greeting().greeting())
        Text(text = data)
    }
}