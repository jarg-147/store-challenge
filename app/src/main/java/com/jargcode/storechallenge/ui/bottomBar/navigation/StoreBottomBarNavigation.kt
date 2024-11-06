package com.jargcode.storechallenge.ui.bottomBar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jargcode.storechallenge.core.domain.Route
import com.jargcode.storechallenge.navigation.transitions.*
import com.jargcode.storechallenge.ui.bottomBar.StoreBottomBarRoute
import kotlinx.serialization.Serializable

@Serializable
data object StoreBottomBar : Route

fun NavGraphBuilder.storeBottomBar() {

    composable<StoreBottomBar>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {

        StoreBottomBarRoute()

    }

}