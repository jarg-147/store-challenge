package com.jargcode.storechallenge.core.network.di

import com.jargcode.storechallenge.core.network.discounts.DemoDiscountsRemoteDataSource
import com.jargcode.storechallenge.core.network.discounts.DemoDiscountsRemoteDataSourceImpl
import com.jargcode.storechallenge.core.network.products.ProductsRemoteDataSource
import com.jargcode.storechallenge.core.network.products.ProductsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    internal abstract fun bindDemoDiscountsRemoteDataSource(
        impl: DemoDiscountsRemoteDataSourceImpl,
    ): DemoDiscountsRemoteDataSource

    @Binds
    internal abstract fun bindProductsRemoteDataSource(
        impl: ProductsRemoteDataSourceImpl,
    ): ProductsRemoteDataSource


}