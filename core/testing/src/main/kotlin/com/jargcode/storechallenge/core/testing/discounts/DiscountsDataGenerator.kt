package com.jargcode.storechallenge.core.testing.discounts

import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem

fun getFakeDiscounts() = listOf(
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