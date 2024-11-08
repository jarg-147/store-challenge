package com.jargcode.storechallenge.core.database.di

import com.jargcode.storechallenge.core.database.StoreDatabase
import com.jargcode.storechallenge.core.database.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DAOModule {

    @Provides
    fun providesCartDao(
        database: StoreDatabase,
    ): CartDao = database.cartDao()

}