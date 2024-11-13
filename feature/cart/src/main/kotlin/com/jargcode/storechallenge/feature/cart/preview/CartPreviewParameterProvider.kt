package com.jargcode.storechallenge.feature.cart.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jargcode.storechallenge.feature.cart.model.CartUi
import com.jargcode.storechallenge.feature.cart.model.CartUiState

class CartPreviewParameterProvider : PreviewParameterProvider<CartUiState> {

    override val values: Sequence<CartUiState>
        get() = sequenceOf(
            CartUiState.Loading,
            CartUiState.Error,
            CartUiState.Success(
                cartProducts = emptyList(),
                totalPriceWithoutDiscounts = "0€"
            ),
            CartUiState.Success(
                cartProducts = CartUi.mock.cartProducts,
                totalPriceWithoutDiscounts = CartUi.mock.totalPriceWithoutDiscounts
            ),
        )

}