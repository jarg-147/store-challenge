package com.jargcode.storechallenge.core.network.products

import com.jargcode.storechallenge.core.network.products.model.ProductsDTO
import kotlinx.coroutines.flow.Flow

interface ProductsRemoteDataSource {
    fun getProducts(): Flow<ProductsDTO>
}