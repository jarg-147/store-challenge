package com.jargcode.storechallenge.core.domain.discounts.model

sealed class Discount(
    open val productId: String,
    open val minQuantity: Int,
) {

    data class FixedPrice(
        override val productId: String,
        override val minQuantity: Int,
        val fixedPrice: Double,
    ) : Discount(
        productId = productId,
        minQuantity = minQuantity
    )

    data class FreeItem(
        override val productId: String,
        override val minQuantity: Int
    ) : Discount(
        productId = productId,
        minQuantity = minQuantity
    )

}