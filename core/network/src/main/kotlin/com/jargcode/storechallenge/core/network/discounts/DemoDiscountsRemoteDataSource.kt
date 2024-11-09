package com.jargcode.storechallenge.core.network.discounts

import com.jargcode.storechallenge.core.network.discounts.model.DiscountDTO

interface DemoDiscountsRemoteDataSource {
    fun getRemoteDiscounts(): List<DiscountDTO>
}