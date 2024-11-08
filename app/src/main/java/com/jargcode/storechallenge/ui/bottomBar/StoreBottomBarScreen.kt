package com.jargcode.storechallenge.ui.bottomBar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jargcode.storechallenge.common.extensions.navigateTo
import com.jargcode.storechallenge.common.extensions.routeName
import com.jargcode.storechallenge.core.designsystem.components.bars.BottomBarDestination
import com.jargcode.storechallenge.core.designsystem.components.bars.StoreBottomBar
import com.jargcode.storechallenge.core.designsystem.preview.DevicePreview
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.navigation.host.BottomBarNavGraph
import com.jargcode.storechallenge.navigation.host.ProductsList

@Composable
fun StoreBottomBarRoute() {

    StoreBottomBarScreen()

}

@Composable
private fun StoreBottomBarScreen() {

    val navController = rememberNavController()

    val destinations = listOf(
        BottomBarDestination(
            route = ProductsList,
            icon = Icons.Rounded.ShoppingBag
        )
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.routeName.orEmpty()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            StoreBottomBar(
                currentRoute = currentDestination,
                destinations = destinations,
                onItemClick = { route ->
                    navController.navigateTo(route) {
                        popUpTo<ProductsList> {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { paddingValues ->
            BottomBarNavGraph(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                navController = navController
            )
        }
    )

}

@DevicePreview
@Composable
private fun StoreBottomBarScreenPreview() {
    PreviewContainer {
        StoreBottomBarScreen()
    }
}