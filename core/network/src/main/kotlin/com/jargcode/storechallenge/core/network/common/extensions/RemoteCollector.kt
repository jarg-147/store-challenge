package com.jargcode.storechallenge.core.network.common.extensions

import com.jargcode.storechallenge.core.domain.common.coroutines.DispatcherProvider
import com.jargcode.storechallenge.core.domain.common.model.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteCollector @Inject constructor(
    private val dispatchers: DispatcherProvider,
) {

    fun <R> fetch(remoteResponse: suspend (() -> Response<R>)): Flow<R> = flow {
        val response = withContext(dispatchers.io) {
            remoteResponse()
        }
        val body = response.body()
        when {
            response.isSuccessful && body != null -> emit(body)
            response.isSuccessful -> emit(Unit as R)
            else -> {
                // Error handling simplified for brevity
                throw Failure.NetworkFailure()
            }
        }
    }

}