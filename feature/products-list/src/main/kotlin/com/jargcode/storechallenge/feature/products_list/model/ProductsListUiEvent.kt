package com.jargcode.storechallenge.feature.products_list.model

sealed interface ProductsListUiEvent {

    data class OnAddProductToCartClick(
        val productId: String,
    ) : ProductsListUiEvent

    data object OnRetryClick : ProductsListUiEvent

}