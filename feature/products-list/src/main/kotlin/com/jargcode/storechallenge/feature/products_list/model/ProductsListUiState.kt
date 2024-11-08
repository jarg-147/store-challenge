package com.jargcode.storechallenge.feature.products_list.model

sealed interface ProductsListUiState {

    data object Loading : ProductsListUiState

    data object Error : ProductsListUiState

    data class Success(val products: List<ProductUi>) : ProductsListUiState

}
