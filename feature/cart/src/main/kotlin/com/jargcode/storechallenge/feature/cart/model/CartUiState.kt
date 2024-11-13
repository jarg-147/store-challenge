package com.jargcode.storechallenge.feature.cart.model

import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi

sealed interface CartUiState {

    data object Loading : CartUiState

    data object Error : CartUiState

    data class Success(
        val cartProducts: List<CartProductUi>,
        val totalPriceWithoutDiscounts: String,
    ) : CartUiState

}