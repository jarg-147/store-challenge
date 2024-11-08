package com.jargcode.storechallenge.core.designsystem.components.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarState: SnackbarHostState? = null,
    containerColor: Color = StoreTheme.backgroundColors.backgroundGrey,
    applyStatusBarPadding: Boolean = true,
    applyNavigationBarPadding: Boolean = true,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = { snackbarState?.let { SnackbarHost(it) } },
        containerColor = containerColor
    ) { paddingValues ->
        val contentModifier = when {
            applyStatusBarPadding && applyNavigationBarPadding -> Modifier.padding(paddingValues)
            applyStatusBarPadding -> Modifier.padding(top = paddingValues.calculateTopPadding())
            applyNavigationBarPadding -> Modifier.padding(bottom = paddingValues.calculateBottomPadding())
            else -> Modifier
        }

        Box(
            modifier = contentModifier,
            content = content
        )
    }
}

@Composable
fun <T> Screen(
    modifier: Modifier = Modifier,
    uiState: T,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarState: SnackbarHostState? = null,
    containerColor: Color = StoreTheme.backgroundColors.backgroundGrey,
    applyStatusBarPadding: Boolean = true,
    applyNavigationBarPadding: Boolean = true,
    content: @Composable (T) -> Unit = {},
) {
    Screen(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarState = snackbarState,
        containerColor = containerColor,
        applyStatusBarPadding = applyStatusBarPadding,
        applyNavigationBarPadding = applyNavigationBarPadding,
    ) {
        AnimatedContent(
            targetState = uiState,
            label = "Screen ui state animated content"
        ) { state ->
            content(state)
        }
    }
}