package com.jargcode.storechallenge.core.testing.di

import com.jargcode.storechallenge.core.common.di.DispatchersModule
import com.jargcode.storechallenge.core.domain.common.coroutines.DispatcherProvider
import com.jargcode.storechallenge.core.testing.TestDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
internal object TestDispatchersModule {

    @Provides
    @Singleton
    fun providesTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()

    @Provides
    fun provideDispatcherProvider(
        testDispatcher: TestDispatcher,
    ): DispatcherProvider = TestDispatchers(testDispatcher = testDispatcher)

}
