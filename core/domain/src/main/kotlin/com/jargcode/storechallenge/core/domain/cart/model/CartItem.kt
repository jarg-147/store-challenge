package com.jargcode.storechallenge.core.domain.cart.model

data class CartItem(
    val id: String,
    val name: String,
    val pricePerUnit: Double,
    val quantity: Int
)