package com.jargcode.storechallenge.core.network.di

import com.jargcode.storechallenge.core.network.products.ProductsRemoteDataSource
import com.jargcode.storechallenge.core.network.products.ProductsRemoteDataSourceImpl
import com.jargcode.storechallenge.core.network.promotions.DemoPromotionsRemoteDataSource
import com.jargcode.storechallenge.core.network.promotions.DemoPromotionsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    internal abstract fun bindDemoPromotionRemoteDataSource(
        impl: DemoPromotionsRemoteDataSourceImpl,
    ): DemoPromotionsRemoteDataSource

    @Binds
    internal abstract fun bindProductsRemoteDataSource(
        impl: ProductsRemoteDataSourceImpl,
    ): ProductsRemoteDataSource


}