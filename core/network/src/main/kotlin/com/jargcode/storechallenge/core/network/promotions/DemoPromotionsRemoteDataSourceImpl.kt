package com.jargcode.storechallenge.core.network.promotions

import com.jargcode.storechallenge.core.network.promotions.model.PromotionDTO
import javax.inject.Inject

class DemoPromotionsRemoteDataSourceImpl @Inject constructor() : DemoPromotionsRemoteDataSource {

    override fun getRemotePromotions(): List<PromotionDTO> = listOf(
        PromotionDTO(
            productId = "VOUCHER",
            type = "FREE_ITEM",
            minQuantity = 2
        ),
        PromotionDTO(
            productId = "TSHIRT",
            type = "FIXED_PRICE",
            minQuantity = 3,
            fixedPrice = 19.0
        )
    )


}