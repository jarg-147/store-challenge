package com.jargcode.storechallenge.core.domain.common.model

sealed class Failure : Throwable() {

    class NetworkFailure : Failure()

}