package com.jargcode.storechallenge.core.domain.discounts.model

sealed class Discount(
    open val productCode: String,
    open val minQuantity: Int,
) {

    data class FixedPrice(
        override val productCode: String,
        override val minQuantity: Int,
        val fixedPrice: Double,
    ) : Discount(
        productCode = productCode,
        minQuantity = minQuantity
    )

    data class FreeItem(
        override val productCode: String,
        override val minQuantity: Int
    ) : Discount(
        productCode = productCode,
        minQuantity = minQuantity
    )

}