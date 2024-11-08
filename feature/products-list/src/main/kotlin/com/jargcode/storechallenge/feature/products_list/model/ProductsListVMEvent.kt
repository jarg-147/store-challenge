package com.jargcode.storechallenge.feature.products_list.model

sealed interface ProductsListVMEvent {

    data object ShowProductAddedToCartSuccess : ProductsListVMEvent

    data object ShowProductAddedToCartError : ProductsListVMEvent

}