package com.jargcode.storechallenge.feature.cart.model

import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi

sealed interface CartUiState {

    data object Loading : CartUiState

    data object Error : CartUiState

    data class Success(
        val items: List<CartItemUi>,
        val totalPrice: String,
    ) : CartUiState

}