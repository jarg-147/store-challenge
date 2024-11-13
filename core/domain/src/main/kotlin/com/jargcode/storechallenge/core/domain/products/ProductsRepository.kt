package com.jargcode.storechallenge.core.domain.products

import com.jargcode.storechallenge.core.domain.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<List<Product>>
}