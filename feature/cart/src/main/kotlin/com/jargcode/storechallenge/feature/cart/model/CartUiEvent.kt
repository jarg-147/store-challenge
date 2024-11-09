package com.jargcode.storechallenge.feature.cart.model

sealed interface CartUiEvent {

    data class OnDeleteItemFromCartClick(val itemCode: String) : CartUiEvent

    data object OnGoToCheckoutClick : CartUiEvent

    data object OnRetryClick : CartUiEvent

}