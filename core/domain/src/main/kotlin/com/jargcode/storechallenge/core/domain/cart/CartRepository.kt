package com.jargcode.storechallenge.core.domain.cart

import com.jargcode.storechallenge.core.domain.cart.model.SavedCartProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addProduct(productCode: String)
    fun getCartCount(): Flow<Int>
    fun getCartProducts(): Flow<List<SavedCartProduct>>
    suspend fun removeCartProduct(productCode: String)
}