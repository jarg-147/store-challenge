package com.jargcode.storechallenge.core.database.dao

import androidx.room.*
import com.jargcode.storechallenge.core.database.model.CartProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_product")
    fun getCartProducts(): Flow<List<CartProductEntity>>

    @Query("SELECT SUM(quantity) FROM cart_product")
    fun getCartCount(): Flow<Int>

    @Query("SELECT * FROM cart_product WHERE product_code = :id")
    suspend fun getCartProduct(id: String): List<CartProductEntity>

    @Upsert
    suspend fun upsertProduct(entity: CartProductEntity)

    @Query("DELETE FROM cart_product WHERE product_code = :productCode")
    suspend fun removeCartProduct(productCode: String)

    suspend fun addProductToCart(productCode: String) {
        val product = getCartProduct(productCode).firstOrNull()
        if (product != null) {
            val currentProductQuantity = product.quantity
            val newProductQuantity = currentProductQuantity + 1
            upsertProduct(product.copy(quantity = newProductQuantity))
        } else {
            upsertProduct(CartProductEntity(productCode, 1))
        }
    }

}
