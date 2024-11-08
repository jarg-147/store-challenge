package com.jargcode.storechallenge.core.database.dao

import androidx.room.*
import com.jargcode.storechallenge.core.database.model.CartItemEntity

@Dao
interface CartDao {

    @Upsert
    suspend fun upsertProduct(entity: CartItemEntity)

    @Query("SELECT * FROM cart_item WHERE product_id = :id")
    suspend fun getCartItem(id: String): List<CartItemEntity>

    suspend fun addProductUnitToCart(productId: String) {
        val item = getCartItem(productId).firstOrNull()
        if (item != null) {
            val currentProductQuantity = item.quantity
            val newProductQuantity = currentProductQuantity + 1
            upsertProduct(item.copy(quantity = newProductQuantity))
        } else {
            upsertProduct(CartItemEntity(productId, 1))
        }
    }

}
