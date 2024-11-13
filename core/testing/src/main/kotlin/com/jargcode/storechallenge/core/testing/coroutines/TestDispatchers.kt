package com.jargcode.storechallenge.core.testing.coroutines

import com.jargcode.storechallenge.core.domain.common.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatchers(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = testDispatcher

    override val mainImmediate: CoroutineDispatcher
        get() = testDispatcher

    override val io: CoroutineDispatcher
        get() = testDispatcher

    override val default: CoroutineDispatcher
        get() = testDispatcher

    override val unconfined: CoroutineDispatcher
        get() = testDispatcher

}