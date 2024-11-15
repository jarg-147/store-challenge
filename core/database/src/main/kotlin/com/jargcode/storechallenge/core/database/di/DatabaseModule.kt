package com.jargcode.storechallenge.core.database.di

import android.content.Context
import androidx.room.Room
import com.jargcode.storechallenge.core.database.StoreDatabase
import com.jargcode.storechallenge.core.database.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesStoreDatabase(
        @ApplicationContext context: Context,
    ): StoreDatabase = Room.databaseBuilder(
        context,
        StoreDatabase::class.java,
        "store-database",
    ).build()

    @Provides
    fun providesCartDao(
        database: StoreDatabase,
    ): CartDao = database.cartDao()

}