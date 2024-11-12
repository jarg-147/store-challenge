package com.jargcode.storechallenge.core.data.discounts

import com.jargcode.storechallenge.core.domain.discounts.DiscountsRepository
import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import com.jargcode.storechallenge.core.network.discounts.DemoDiscountsRemoteDataSource
import com.jargcode.storechallenge.core.network.discounts.model.toDiscount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiscountsRepositoryImpl @Inject constructor(
    private val discountsRemoteDataSource: DemoDiscountsRemoteDataSource,
) : DiscountsRepository {

    override fun getDiscounts(): Flow<List<Discount>> = flow {
        val remoteDiscounts = discountsRemoteDataSource.getRemoteDiscounts()
        val mappedDiscounts = remoteDiscounts
            .filter { discountDTO -> discountDTO.minQuantity > 0 }
            .mapNotNull { discountDTO ->
                discountDTO.toDiscount()
            }
        emit(mappedDiscounts)
    }

}