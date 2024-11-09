package com.jargcode.storechallenge.feature.products_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.ProductionQuantityLimits
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
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiEvent
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiEvent.OnAddProductToCartClick
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiEvent.OnRetryClick
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiState
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiState.*
import com.jargcode.storechallenge.feature.products_list.model.ProductsListVMEvent.ShowProductAddedToCartError
import com.jargcode.storechallenge.feature.products_list.model.ProductsListVMEvent.ShowProductAddedToCartSuccess
import com.jargcode.storechallenge.feature.products_list.preview.ProductsListPreviewParameterProvider
import com.jargcode.storechallenge.feature.products_list.ui.ProductListContent
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Composable
fun ProductsListRoute(
    viewModel: ProductsListViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarState = remember { SnackbarHostState() }

    val uiEvent = rememberUiEvent<ProductsListUiEvent> { action ->
        when (action) {
            is OnAddProductToCartClick -> {
                viewModel.onAddProductToCartClick(productId = action.productId)
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
            ShowProductAddedToCartError -> {
                scope.coroutineContext.cancelChildren()
                scope.launch {
                    snackbarState.showSnackbar(
                        message = context.getString(R.string.product_added_to_cart_error),
                        withDismissAction = true
                    )
                }
            }

            ShowProductAddedToCartSuccess -> {
                scope.coroutineContext.cancelChildren()
                scope.launch {
                    snackbarState.showSnackbar(
                        message = context.getString(R.string.product_added_to_cart_success),
                        withDismissAction = true
                    )
                }
            }
        }
    }

    ProductsListScreen(
        uiState = uiState,
        snackbarState = snackbarState,
        onUiEvent = uiEvent,
    )

}

@Composable
private fun ProductsListScreen(
    uiState: ProductsListUiState,
    onUiEvent: (ProductsListUiEvent) -> Unit,
    snackbarState: SnackbarHostState,
) {
    Screen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.products_list_title)
            )
        },
        containerColor = StoreTheme.backgroundColors.backgroundGrey,
        snackbarState = snackbarState,
        applyNavigationBarPadding = false
    ) { state ->
        when (state) {
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
                    title = stringResource(R.string.error_placeholder_title),
                    description = stringResource(R.string.error_placeholder_text),
                    buttonText = stringResource(R.string.error_placeholder_button),
                    onButtonClick = {
                        onUiEvent(OnRetryClick)
                    }
                )
            }

            is Success -> {
                val products = state.products
                if (products.isEmpty()) {
                    EmptyView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(16.dp),
                        icon = Icons.Rounded.ProductionQuantityLimits,
                        title = stringResource(R.string.empty_placeholder_title),
                        description = stringResource(R.string.empty_placeholder_text),
                    )
                } else {
                    ProductListContent(
                        modifier = Modifier.fillMaxSize(),
                        products = products,
                        onUiEvent = onUiEvent,
                    )
                }
            }
        }
    }
}

@DevicePreview
@Composable
private fun ProductsListScreenPreview(
    @PreviewParameter(ProductsListPreviewParameterProvider::class)
    uiState: ProductsListUiState,
) {
    PreviewContainer {
        ProductsListScreen(
            uiState = uiState,
            snackbarState = remember { SnackbarHostState() },
            onUiEvent = {}
        )
    }
}