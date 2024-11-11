package com.jargcode.storechallenge.core.database.model

import androidx.room.*
import com.jargcode.storechallenge.core.domain.cart.model.SavedCartProduct

@Entity(
    tableName = "cart_product",
)
data class CartProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_code")
    val productCode: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
)

fun CartProductEntity.toSavedCartProduct() = SavedCartProduct(
    productCode = productCode,
    quantity = quantity,
)