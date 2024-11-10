package com.jargcode.storechallenge.core.domain.discounts.model

import com.jargcode.storechallenge.core.domain.products.model.Product

data class AppliedDiscount(
    val product: Product,
    val discount: Discount,
    val totalDiscount: Double,
    val timesApplied: Int
)