package com.jargcode.storechallenge.core.network.products.model

import com.jargcode.storechallenge.core.domain.products.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductsDTO(
    val products: List<ProductDTO>,
)

@Serializable
data class ProductDTO(
    val code: String,
    val name: String,
    val price: Double,
)

fun ProductDTO.toProduct() = Product(
    code = code,
    name = name,
    price = price,
)