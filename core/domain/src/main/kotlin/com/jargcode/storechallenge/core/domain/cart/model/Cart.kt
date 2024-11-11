package com.jargcode.storechallenge.core.domain.cart.model

import com.jargcode.storechallenge.core.domain.products.model.Product

data class Cart(
    val cartProducts: List<CartProduct>,
    val totalPriceWithoutDiscounts: Double,
) {

    data class CartProduct(
        val product: Product,
        val quantity: Int,
    ) {

        val total: Double
            get() = product.price * quantity

    }

}