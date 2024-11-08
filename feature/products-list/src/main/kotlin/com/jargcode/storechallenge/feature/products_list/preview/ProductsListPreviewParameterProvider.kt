package com.jargcode.storechallenge.feature.products_list.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jargcode.storechallenge.feature.products_list.model.ProductUi
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiState

class ProductsListPreviewParameterProvider : PreviewParameterProvider<ProductsListUiState> {

    override val values: Sequence<ProductsListUiState>
        get() = sequenceOf(
            ProductsListUiState.Loading,
            ProductsListUiState.Error,
            ProductsListUiState.Success(listOf()),
            ProductsListUiState.Success(listOf(ProductUi.mock)),
        )

}