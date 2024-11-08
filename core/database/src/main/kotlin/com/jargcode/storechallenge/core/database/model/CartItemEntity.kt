package com.jargcode.storechallenge.core.database.model

import androidx.room.*

@Entity(
    tableName = "cart_item",
)
data class CartItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
)