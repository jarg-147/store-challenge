package com.jargcode.storechallenge.core.testing.products

import com.jargcode.storechallenge.core.domain.products.ProductsRepository
import com.jargcode.storechallenge.core.domain.products.model.Product
import kotlinx.coroutines.flow.*

class FakeProductsRepository : ProductsRepository {

    var getProductsThrowError = false

    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())

    override fun getProducts(): Flow<List<Product>> = if (!getProductsThrowError) {
        _products
    } else {
        flow { throw Exception("Fake error") }
    }

    fun setProducts(products: List<Product>) {
        _products.update { products }
    }

}