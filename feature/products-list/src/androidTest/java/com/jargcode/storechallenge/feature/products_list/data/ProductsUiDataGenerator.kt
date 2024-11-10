package com.jargcode.storechallenge.feature.products_list.data

import com.jargcode.storechallenge.core.testing.products.getFakeProducts
import com.jargcode.storechallenge.feature.products_list.model.toProductUi

fun getFakeProductsUi() = getFakeProducts()
    .map { product ->
        product.toProductUi()
    }