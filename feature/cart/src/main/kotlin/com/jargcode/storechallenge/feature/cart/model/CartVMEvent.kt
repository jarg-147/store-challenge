package com.jargcode.storechallenge.feature.cart.model

sealed interface CartVMEvent {

    data object ShowProductDeleteSuccess : CartVMEvent

    data object ShowProductDeleteError : CartVMEvent

}