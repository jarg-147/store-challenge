package com.jargcode.storechallenge.core.data.cart

import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
) : CartRepository {

    override suspend fun addProduct(productId: String) {
    cartDao.addProductUnitToCart(productId = productId)
    }

}