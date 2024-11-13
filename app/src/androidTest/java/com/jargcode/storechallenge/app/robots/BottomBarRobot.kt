package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.R
import com.jargcode.storechallenge.app.StoreComposeRule

class BottomBarRobot(
    private val composeTestRule: StoreComposeRule,
) {

    fun assertBadgeIsDisplayed(quantity: Int) = apply {
        composeTestRule
            .onNodeWithText(quantity.toString(), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun navigateToCart() = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.bottom_bar_cart_label), useUnmergedTree = true)
            .performClick()
    }

}