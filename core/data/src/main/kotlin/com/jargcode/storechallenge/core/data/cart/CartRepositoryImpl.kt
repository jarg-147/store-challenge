package com.jargcode.storechallenge.core.data.cart

import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.database.model.toSavedCartProduct
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
) : CartRepository {

    override suspend fun addProduct(productCode: String) {
        cartDao.addProductToCart(productCode = productCode)
    }

    override fun getCartCount(): Flow<Int> = cartDao.getCartCount()

    override fun getCartProducts(): Flow<List<SavedCartProduct>> = cartDao
        .getCartProducts()
        .map { cartProductsEntities ->
            cartProductsEntities.map { cartProductEntity ->
                cartProductEntity.toSavedCartProduct()
            }
        }

    override suspend fun removeCartProduct(productCode: String) {
        cartDao.removeCartProduct(productCode = productCode)
    }

}