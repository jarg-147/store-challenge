package com.jargcode.storechallenge.core.domain.promotions.model

sealed class Promotion(
    open val productId: String,
    open val minQuantity: Int,
) {

    data class FixedPrice(
        override val productId: String,
        override val minQuantity: Int,
        val fixedPrice: Double,
    ) : Promotion(
        productId = productId,
        minQuantity = minQuantity
    )

    data class FreeItem(
        override val productId: String,
        override val minQuantity: Int
    ) : Promotion(
        productId = productId,
        minQuantity = minQuantity
    )

}