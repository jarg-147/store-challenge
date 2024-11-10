package com.jargcode.storechallenge.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jargcode.storechallenge.common.extensions.navigateTo
import com.jargcode.storechallenge.common.extensions.pop
import com.jargcode.storechallenge.core.domain.common.navigation.Route
import com.jargcode.storechallenge.feature.checkout.CheckoutRoute
import com.jargcode.storechallenge.navigation.transitions.*
import com.jargcode.storechallenge.ui.bottomBar.navigation.StoreBottomBar
import com.jargcode.storechallenge.ui.bottomBar.navigation.storeBottomBar
import kotlinx.serialization.Serializable

@Composable
fun StoreNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = StoreBottomBar
    ) {
        storeBottomBar(
            onNavigate = navController::navigateTo
        )
        checkoutScreen(
            onBackClick = navController::pop
        )
    }
}

@Serializable
data object Checkout : Route

private fun NavGraphBuilder.checkoutScreen(
    onBackClick: () -> Unit,
) {
    composable<Checkout>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        CheckoutRoute(
            onBackClick = onBackClick
        )
    }
}