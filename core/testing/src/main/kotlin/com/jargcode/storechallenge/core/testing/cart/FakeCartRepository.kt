package com.jargcode.storechallenge.core.testing.cart

import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartItem
import kotlinx.coroutines.flow.*

class FakeCartRepository : CartRepository {

    private val _products: MutableStateFlow<List<SavedCartItem>> = MutableStateFlow(emptyList())
    var addProductReturnsError = false

    override suspend fun addProduct(productCode: String) {
        if (addProductReturnsError) {
            throw Exception("Something went wrong")
        } else {
            _products.update { state ->
                state + listOf(SavedCartItem(productCode = productCode, quantity = 1))
            }
        }
    }

    override fun getCartCount(): Flow<Int> = _products.map { it.size }

    override fun getUserCartItems(): Flow<List<SavedCartItem>> = _products

    override suspend fun removeCartProduct(productCode: String) {
        _products.update { state ->
            state.filterNot { item -> item.productCode == productCode }
        }
    }

}