package com.jargcode.storechallenge.core.domain.products.model

import com.jargcode.storechallenge.core.domain.promotions.model.Promotion

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val promotion: Promotion? = null
)
