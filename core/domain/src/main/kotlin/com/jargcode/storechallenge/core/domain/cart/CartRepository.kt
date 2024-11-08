package com.jargcode.storechallenge.core.domain.cart

interface CartRepository {
    suspend fun addProduct(productId: String)
}