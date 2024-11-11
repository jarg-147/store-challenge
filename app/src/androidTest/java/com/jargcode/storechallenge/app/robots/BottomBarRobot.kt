package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class BottomBarRobot(
    private val composeTestRule: StoreComposeRule,
) {

    fun assertBadgeIsDisplayed(quantity: String) = apply {
        runBlocking {
            delay(100)
            composeTestRule.awaitIdle()
            composeTestRule
                .onNodeWithText(quantity, useUnmergedTree = true)
                .assertIsDisplayed()
        }
    }

    fun navigateToCart() = apply {
        composeTestRule
            .onNodeWithText("Cart", useUnmergedTree = true)
            .performClick()
    }

}