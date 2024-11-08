package com.jargcode.storechallenge.feature.products_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.feature.products_list.model.ProductUi
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiEvent
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiEvent.OnAddProductToCartClick

@Composable
fun ProductListContent(
    modifier: Modifier = Modifier,
    products: List<ProductUi>,
    onUiEvent: (ProductsListUiEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductCard(
                modifier = Modifier.fillMaxWidth(),
                product = product,
                onAddToCartClick = {
                    onUiEvent(OnAddProductToCartClick(productId = product.id))
                }
            )
        }
    }
}

@WidgetPreview
@Composable
private fun ProductListContentPreview() {
    PreviewContainer {
        ProductListContent(
            modifier = Modifier.fillMaxSize(),
            products = listOf(
                ProductUi.mock,
                ProductUi.mock.copy(id = "2"),
                ProductUi.mock.copy(id = "3"),
            ),
            onUiEvent = {}
        )
    }
}