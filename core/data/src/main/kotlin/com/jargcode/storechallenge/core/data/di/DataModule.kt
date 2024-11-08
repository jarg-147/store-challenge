package com.jargcode.storechallenge.core.data.di

import com.jargcode.storechallenge.core.data.cart.CartRepositoryImpl
import com.jargcode.storechallenge.core.data.products.ProductsRepositoryImpl
import com.jargcode.storechallenge.core.data.promotions.PromotionsRepositoryImpl
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.products.ProductsRepository
import com.jargcode.storechallenge.core.domain.promotions.PromotionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl,
    ): CartRepository

    @Binds
    internal abstract fun bindProductRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository

    @Binds
    internal abstract fun bindPromotionRepository(
        promotionsRepositoryImpl: PromotionsRepositoryImpl
    ): PromotionsRepository

}