package com.jargcode.storechallenge.core.domain.cart.model

data class Cart(
    val items: List<CartItem>,
    val totalPriceWithoutDiscounts: Double,
)