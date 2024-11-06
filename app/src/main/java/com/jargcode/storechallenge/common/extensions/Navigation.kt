package com.jargcode.storechallenge.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.*

fun NavController.pop() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}

fun <T : Any> NavController.navigateTo(route: T, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        navigate(route) {
            navOptions(this)
        }
    }
}

val NavDestination.routeName
    get() = route?.substringAfterLast(".").orEmpty()