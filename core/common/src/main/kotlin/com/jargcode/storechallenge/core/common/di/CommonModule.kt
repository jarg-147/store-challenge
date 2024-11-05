package com.jargcode.storechallenge.core.common.di

import com.jargcode.storechallenge.core.common.StandardDispatchers
import com.jargcode.storechallenge.core.domain.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = StandardDispatchers()

}