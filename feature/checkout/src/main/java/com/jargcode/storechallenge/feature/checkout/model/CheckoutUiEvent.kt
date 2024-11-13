package com.jargcode.storechallenge.feature.checkout.model

sealed interface CheckoutUiEvent {

    data object OnBackClick : CheckoutUiEvent

    data object OnPayClick : CheckoutUiEvent

    data object OnRetryClick : CheckoutUiEvent

}