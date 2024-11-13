package com.jargcode.storechallenge.core.network.products

import com.jargcode.storechallenge.core.network.products.model.ProductsDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiService {

    @GET("6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getRemoteProducts(): Response<ProductsDTO>

}