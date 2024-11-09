package com.jargcode.storechallenge.core.database.model

import androidx.room.*
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartItem

@Entity(
    tableName = "cart_item",
)
data class CartItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_code")
    val productCode: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
)

fun CartItemEntity.toSavedCartItem() = SavedCartItem(
    productCode = productCode,
    quantity = quantity,
)