package com.jargcode.storechallenge.feature.checkout

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jargcode.storechallenge.core.designsystem.components.bars.Toolbar
import com.jargcode.storechallenge.core.designsystem.components.content.EmptyView
import com.jargcode.storechallenge.core.designsystem.components.content.Screen
import com.jargcode.storechallenge.core.designsystem.components.loading.LoadingIndicator
import com.jargcode.storechallenge.core.designsystem.preview.DevicePreview
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.ui.utils.extensions.rememberUiEvent
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiEvent
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiEvent.*
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState.*
import com.jargcode.storechallenge.feature.checkout.preview.CheckoutPreviewParameterProvider
import com.jargcode.storechallenge.feature.checkout.ui.CheckoutContent

@Composable
fun CheckoutRoute(
    viewModel: CheckoutViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val uiEvent = rememberUiEvent<CheckoutUiEvent> { action ->
        when (action) {
            OnPayClick -> {
                // Unnecessary implementation
            }

            OnRetryClick -> {
                viewModel.onRetryClick()
            }

            OnBackClick -> {
                onBackClick()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    CheckoutScreen(
        uiState = uiState,
        onUiEvent = uiEvent,
    )

}

@Composable
@VisibleForTesting
internal fun CheckoutScreen(
    uiState: CheckoutUiState,
    onUiEvent: (CheckoutUiEvent) -> Unit,
) {
    Screen(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.checkout_summary_toolbar),
                onBackIconClick = {
                    onUiEvent(OnBackClick)
                }
            )
        },
        containerColor = StoreTheme.backgroundColors.backgroundGrey,
        applyNavigationBarPadding = false
    ) {
        when (uiState) {
            is Loading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is Error -> {
                EmptyView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                        .navigationBarsPadding(),
                    icon = Icons.Rounded.ErrorOutline,
                    title = stringResource(R.string.checkout_summary_error_title),
                    description = stringResource(R.string.checkout_summary_error_text),
                    buttonText = stringResource(R.string.checkout_summary_error_button),
                    onButtonClick = {
                        onUiEvent(OnRetryClick)
                    }
                )
            }

            is Success -> {
                CheckoutContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(stringResource(R.string.checkout_content_test_tag)),
                    products = uiState.products,
                    subtotal = uiState.subtotal,
                    appliedDiscounts = uiState.appliedDiscounts,
                    total = uiState.total,
                    onUiEvent = onUiEvent
                )
            }
        }
    }
}


@DevicePreview
@Composable
private fun CheckoutScreenPreview(
    @PreviewParameter(CheckoutPreviewParameterProvider::class)
    uiState: CheckoutUiState,
) {
    PreviewContainer {
        CheckoutScreen(
            uiState = uiState,
            onUiEvent = {}
        )
    }
}