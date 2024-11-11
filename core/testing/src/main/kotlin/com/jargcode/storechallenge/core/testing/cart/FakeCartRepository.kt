package com.jargcode.storechallenge.core.testing.cart

import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartProduct
import kotlinx.coroutines.flow.*

class FakeCartRepository : CartRepository {

    private val _products: MutableStateFlow<List<SavedCartProduct>> = MutableStateFlow(emptyList())

    var addProductReturnsError = false
    var removeProductReturnsError = false

    override suspend fun addProduct(productCode: String) {
        if (addProductReturnsError) {
            throw Exception("Something went wrong")
        } else {
            _products.update { state ->
                state + listOf(SavedCartProduct(productCode = productCode, quantity = 1))
            }
        }
    }

    override fun getCartCount(): Flow<Int> = _products.map { it.size }

    override fun getCartProducts(): Flow<List<SavedCartProduct>> = _products

    override suspend fun removeCartProduct(productCode: String) {
        if (removeProductReturnsError) {
            throw Exception("Something went wrong")
        } else {
            _products.update { state ->
                state.filterNot { product -> product.productCode == productCode }
            }
        }
    }

    fun setCartProducts(products: List<SavedCartProduct>) {
        _products.update { products }
    }

}