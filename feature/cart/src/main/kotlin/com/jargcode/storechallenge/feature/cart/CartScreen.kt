package com.jargcode.storechallenge.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.jargcode.storechallenge.core.ui.utils.extensions.EventObserver
import com.jargcode.storechallenge.core.ui.utils.extensions.rememberUiEvent
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent.*
import com.jargcode.storechallenge.feature.cart.model.CartUiState
import com.jargcode.storechallenge.feature.cart.model.CartUiState.*
import com.jargcode.storechallenge.feature.cart.model.CartVMEvent.ShowProductDeleteError
import com.jargcode.storechallenge.feature.cart.model.CartVMEvent.ShowProductDeleteSuccess
import com.jargcode.storechallenge.feature.cart.preview.CartPreviewParameterProvider
import com.jargcode.storechallenge.feature.cart.ui.CartContent
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Composable
fun CartRoute(
    viewModel: CartViewModel = hiltViewModel(),
    onNavigateToCheckout: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarState = remember { SnackbarHostState() }

    val uiEvent = rememberUiEvent<CartUiEvent> { action ->
        when (action) {
            is OnDeleteItemFromCartClick -> {
                viewModel.onDeleteItemClick(productCode = action.itemCode)
            }

            is OnGoToCheckoutClick -> {
                onNavigateToCheckout()
            }

            is OnRetryClick -> {
                viewModel.onRetryClick()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    EventObserver(
        flow = viewModel.vmEvent,
    ) { event ->
        when (event) {
            is ShowProductDeleteSuccess -> {
                scope.coroutineContext.cancelChildren()
                scope.launch {
                    snackbarState.showSnackbar(
                        message = context.getString(R.string.delete_product_from_cart_success),
                        withDismissAction = true
                    )
                }
            }

            is ShowProductDeleteError -> {
                scope.coroutineContext.cancelChildren()
                scope.launch {
                    snackbarState.showSnackbar(
                        message = context.getString(R.string.delete_product_from_cart_error),
                        withDismissAction = true
                    )
                }
            }
        }
    }

    CartScreen(
        uiState = uiState,
        snackbarState = snackbarState,
        onUiEvent = uiEvent,
    )

}

@Composable
private fun CartScreen(
    uiState: CartUiState,
    snackbarState: SnackbarHostState,
    onUiEvent: (CartUiEvent) -> Unit,
) {
    Screen(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.cart_title)
            )
        },
        containerColor = StoreTheme.backgroundColors.backgroundGrey,
        snackbarState = snackbarState,
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
                        .padding(16.dp),
                    icon = Icons.Rounded.ErrorOutline,
                    title = stringResource(R.string.cart_error_title),
                    description = stringResource(R.string.cart_error_text),
                    buttonText = stringResource(R.string.cart_error_button),
                    onButtonClick = {
                        onUiEvent(OnRetryClick)
                    }
                )
            }

            is Success -> {
                if (uiState.items.isEmpty()) {
                    EmptyView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(16.dp),
                        icon = Icons.Rounded.AddShoppingCart,
                        title = stringResource(R.string.cart_empty_title),
                        description = stringResource(R.string.cart_empty_text)
                    )
                } else {
                    CartContent(
                        modifier = Modifier.fillMaxSize(),
                        items = uiState.items,
                        total = uiState.totalPrice,
                        onUiEvent = onUiEvent
                    )
                }
            }
        }
    }
}


@DevicePreview
@Composable
private fun CartScreenPreview(
    @PreviewParameter(CartPreviewParameterProvider::class)
    uiState: CartUiState,
) {
    PreviewContainer {
        CartScreen(
            uiState = uiState,
            snackbarState = remember { SnackbarHostState() },
            onUiEvent = {}
        )
    }
}