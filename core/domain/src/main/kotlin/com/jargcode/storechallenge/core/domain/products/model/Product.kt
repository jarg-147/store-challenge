package com.jargcode.storechallenge.core.domain.products.model

import com.jargcode.storechallenge.core.domain.discounts.model.Discount

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val discount: Discount? = null
)
