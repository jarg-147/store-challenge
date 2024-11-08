package com.jargcode.storechallenge.core.domain.promotions

import com.jargcode.storechallenge.core.domain.promotions.model.Promotion
import kotlinx.coroutines.flow.Flow

interface PromotionsRepository {
    fun getPromotions(): Flow<List<Promotion>>
}