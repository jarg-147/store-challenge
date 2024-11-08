package com.jargcode.storechallenge.core.network.products

import com.jargcode.storechallenge.core.network.common.extensions.RemoteCollector
import com.jargcode.storechallenge.core.network.products.model.ProductsDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRemoteDataSourceImpl @Inject constructor(
    private val remoteCollector: RemoteCollector,
    private val apiService: ProductsApiService
) : ProductsRemoteDataSource {

    override fun getProducts(): Flow<ProductsDTO> = remoteCollector.fetch {
        apiService.getRemoteProducts()
    }

}