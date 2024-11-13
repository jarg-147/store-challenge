package com.jargcode.storechallenge.core.testing.discounts

import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem

fun discounts() = listOf(
    FreeItem(
        productCode = "VOUCHER",
        minQuantity = 2
    ),
    FixedPrice(
        productCode = "TSHIRT",
        minQuantity = 3,
        fixedPrice = 19.0
    )
)

fun freeDiscount(
    productCode: String,
    minQuantity: Int,
) = FreeItem(
    productCode = productCode,
    minQuantity = minQuantity
)

fun fixedPriceDiscount(
    productCode: String,
    minQuantity: Int,
    fixedPrice: Double,
) = FixedPrice(
    productCode = productCode,
    minQuantity = minQuantity,
    fixedPrice = fixedPrice
)