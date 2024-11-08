package com.jargcode.storechallenge.core.ui.utils.extensions

import androidx.compose.runtime.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// This is a workaround to avoid recomposition when using unstable lambdas like viewModel.foo()
@Composable
fun <T> rememberUiEvent(event: (T) -> Unit): (T) -> Unit = remember { { event(it) } }

@Composable
fun <T> EventObserver(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collect: suspend (T) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            lifecycle.repeatOnLifecycle(lifecycleState) {
                flow.collect(collect)
            }
        }
    }
}