package com.jargcode.storechallenge.core.data.products

import com.jargcode.storechallenge.core.domain.products.ProductsRepository
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.core.network.products.ProductsRemoteDataSource
import com.jargcode.storechallenge.core.network.products.model.toProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override fun getProducts(): Flow<List<Product>> = productsRemoteDataSource
        .getProducts()
        .map { productsDTO ->
            productsDTO.products.map { productDTO ->
                productDTO.toProduct()
            }
        }

}