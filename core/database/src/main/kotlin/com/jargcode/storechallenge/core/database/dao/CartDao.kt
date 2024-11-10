package com.jargcode.storechallenge.core.database.dao

import androidx.room.*
import com.jargcode.storechallenge.core.database.model.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_item")
    fun getUserCartItems(): Flow<List<CartItemEntity>>

    @Query("SELECT SUM(quantity) FROM cart_item")
    fun getCartCount(): Flow<Int>

    @Upsert
    suspend fun upsertProduct(entity: CartItemEntity)

    @Query("SELECT * FROM cart_item WHERE product_code = :id")
    suspend fun getCartItem(id: String): List<CartItemEntity>

    suspend fun addProductUnitToCart(productCode: String) {
        val item = getCartItem(productCode).firstOrNull()
        if (item != null) {
            val currentProductQuantity = item.quantity
            val newProductQuantity = currentProductQuantity + 1
            upsertProduct(item.copy(quantity = newProductQuantity))
        } else {
            upsertProduct(CartItemEntity(productCode, 1))
        }
    }

    @Query("DELETE FROM cart_item WHERE product_code = :productCode")
    suspend fun removeCartProduct(productCode: String)

}
