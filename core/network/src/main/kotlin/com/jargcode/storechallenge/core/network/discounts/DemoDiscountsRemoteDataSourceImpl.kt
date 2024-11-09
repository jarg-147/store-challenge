package com.jargcode.storechallenge.core.network.discounts

import com.jargcode.storechallenge.core.network.discounts.model.DiscountDTO
import javax.inject.Inject

class DemoDiscountsRemoteDataSourceImpl @Inject constructor() : DemoDiscountsRemoteDataSource {

    // Fake implementation of the remote data source for demo purposes.
    override fun getRemoteDiscounts(): List<DiscountDTO> = listOf(
        DiscountDTO(
            productId = "VOUCHER",
            type = "FREE_ITEM",
            minQuantity = 2
        ),
        DiscountDTO(
            productId = "TSHIRT",
            type = "FIXED_PRICE",
            minQuantity = 3,
            fixedPrice = 19.0
        )
    )

}