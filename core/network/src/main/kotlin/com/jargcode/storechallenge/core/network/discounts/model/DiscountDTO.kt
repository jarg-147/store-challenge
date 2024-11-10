package com.jargcode.storechallenge.core.network.discounts.model

import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import kotlinx.serialization.Serializable

@Serializable
data class DiscountDTO(
    val productId: String,
    val type: String,
    val minQuantity: Int,
    val fixedPrice: Double? = null,
)

fun DiscountDTO.toDiscount(): Discount? = when {
    type.equals("FIXED_PRICE", ignoreCase = true) -> {
        fixedPrice?.let { price ->
            FixedPrice(
                productCode = productId,
                minQuantity = minQuantity,
                fixedPrice = price
            )
        }
    }

    type.equals("FREE_ITEM", ignoreCase = true) -> {
        FreeItem(
            productCode = productId,
            minQuantity = minQuantity,
        )
    }

    else -> null
}