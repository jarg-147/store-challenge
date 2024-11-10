package com.jargcode.storechallenge.core.domain.cart

import com.jargcode.storechallenge.core.domain.cart.model.SavedCartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addProduct(productCode: String)
    fun getCartCount(): Flow<Int>
    fun getUserCartItems(): Flow<List<SavedCartItem>>
    suspend fun removeCartProduct(productCode: String)
}