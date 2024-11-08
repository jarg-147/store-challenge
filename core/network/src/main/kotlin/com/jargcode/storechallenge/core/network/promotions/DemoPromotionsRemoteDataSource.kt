package com.jargcode.storechallenge.core.network.promotions

import com.jargcode.storechallenge.core.network.promotions.model.PromotionDTO

interface DemoPromotionsRemoteDataSource {
    fun getRemotePromotions(): List<PromotionDTO>
}