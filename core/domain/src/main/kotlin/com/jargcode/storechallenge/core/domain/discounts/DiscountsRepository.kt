package com.jargcode.storechallenge.core.domain.discounts

import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import kotlinx.coroutines.flow.Flow

interface DiscountsRepository {
    fun getDiscounts(): Flow<List<Discount>>
}