package com.jargcode.storechallenge.feature.cart.model

sealed interface CartUiEvent {

    data class OnDeleteProductFromCartClick(
        val productCode: String,
    ) : CartUiEvent

    data object OnGoToCheckoutClick : CartUiEvent

    data object OnRetryClick : CartUiEvent

}