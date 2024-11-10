package com.jargcode.storechallenge.core.data.cart

import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.database.model.toSavedCartItem
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
) : CartRepository {

    override suspend fun addProduct(productCode: String) {
        cartDao.addProductUnitToCart(productCode = productCode)
    }

    override fun getCartCount(): Flow<Int> = cartDao.getCartCount()

    override fun getUserCartItems(): Flow<List<SavedCartItem>> = cartDao
        .getUserCartItems()
        .map {  cartItemEntities ->
            cartItemEntities.map { cartItemEntity ->
                cartItemEntity.toSavedCartItem()
            }
        }

    override suspend fun removeCartProduct(productCode: String) {
        cartDao.removeCartProduct(productCode = productCode)
    }

}