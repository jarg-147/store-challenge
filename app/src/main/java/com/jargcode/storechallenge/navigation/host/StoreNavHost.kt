package com.jargcode.storechallenge.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jargcode.storechallenge.ui.bottomBar.navigation.StoreBottomBar
import com.jargcode.storechallenge.ui.bottomBar.navigation.storeBottomBar

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

        storeBottomBar()

    }
}