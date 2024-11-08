package com.jargcode.storechallenge.core.data.promotions

import com.jargcode.storechallenge.core.domain.promotions.PromotionsRepository
import com.jargcode.storechallenge.core.domain.promotions.model.Promotion
import com.jargcode.storechallenge.core.network.promotions.DemoPromotionsRemoteDataSource
import com.jargcode.storechallenge.core.network.promotions.model.toPromotion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PromotionsRepositoryImpl @Inject constructor(
    private val promotionsRemoteDataSource: DemoPromotionsRemoteDataSource,
) : PromotionsRepository {

    override fun getPromotions(): Flow<List<Promotion>> = flow {
        val remotePromotions = promotionsRemoteDataSource.getRemotePromotions()
        val mappedPromotions = remotePromotions.mapNotNull { promotionDTO ->
            promotionDTO.toPromotion()
        }
        emit(mappedPromotions)
    }

}