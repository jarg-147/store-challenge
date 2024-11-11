package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule

class CartRobot(
    private val composeTestRule: StoreComposeRule,
) {

    fun assertProductIsInCart(
        productName: String,
        productQuantity: String,
    ) = apply {
        composeTestRule
            .onNodeWithText(productName, useUnmergedTree = true)
            .assertIsDisplayed()
            .onSiblings()
            .assertAny(hasTextExactly(productQuantity))
    }

    fun assertTotalPrice(total: String) = apply {
        composeTestRule
            .onNodeWithText(total, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun navigateToCheckout() = apply {
        composeTestRule
            .onNodeWithText("Checkout", useUnmergedTree = true)
            .performClick()
    }

}