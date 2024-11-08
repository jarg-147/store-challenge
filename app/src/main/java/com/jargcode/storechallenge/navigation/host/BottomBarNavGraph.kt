package com.jargcode.storechallenge.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jargcode.storechallenge.core.domain.common.navigation.Route
import com.jargcode.storechallenge.feature.products_list.ProductsListRoute
import com.jargcode.storechallenge.navigation.transitions.fadeInTransition
import com.jargcode.storechallenge.navigation.transitions.fadeOutTransition
import kotlinx.serialization.Serializable

@Composable
fun BottomBarNavGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ProductsList
    ) {
        productsListScreen()

    }
}

@Serializable
data object ProductsList : Route
private fun NavGraphBuilder.productsListScreen() {
    composable<ProductsList>(
        enterTransition = fadeInTransition,
        exitTransition = fadeOutTransition,
        popEnterTransition = fadeInTransition,
        popExitTransition = fadeOutTransition
    ) {
        ProductsListRoute()
    }
}