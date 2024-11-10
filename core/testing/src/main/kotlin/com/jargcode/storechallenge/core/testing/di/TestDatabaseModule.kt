package com.jargcode.storechallenge.core.testing.di

import android.content.Context
import androidx.room.Room
import com.jargcode.storechallenge.core.database.StoreDatabase
import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.database.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class],
)
internal object TestDatabaseModule {

    @Provides
    @Singleton
    fun providesStoreDatabase(
        @ApplicationContext context: Context,
    ): StoreDatabase = Room.inMemoryDatabaseBuilder(
        context,
        StoreDatabase::class.java
    ).build()

    @Provides
    fun providesCartDao(
        database: StoreDatabase,
    ): CartDao = database.cartDao()

}