package com.jargcode.storechallenge.core.network.promotions.model

import com.jargcode.storechallenge.core.domain.promotions.model.Promotion
import com.jargcode.storechallenge.core.domain.promotions.model.Promotion.FixedPrice
import com.jargcode.storechallenge.core.domain.promotions.model.Promotion.FreeItem
import kotlinx.serialization.Serializable

@Serializable
data class PromotionDTO(
    val productId: String,
    val type: String,
    val minQuantity: Int,
    val fixedPrice: Double? = null,
)

fun PromotionDTO.toPromotion(): Promotion? = when {
    type.equals("FIXED_PRICE", ignoreCase = true) -> {
        fixedPrice?.let { price ->
            FixedPrice(
                productId = productId,
                minQuantity = minQuantity,
                fixedPrice = price
            )
        }
    }

    type.equals("FREE_ITEM", ignoreCase = true) -> {
        FreeItem(
            productId = productId,
            minQuantity = minQuantity,
        )
    }

    else -> null
}