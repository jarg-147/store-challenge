package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.R

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

    fun assertTotalPrice(
        cartWithDiscounts: Boolean = false,
        total: Double,
    ) = apply {

        val cartTotal = if (cartWithDiscounts) {
            "*${total.toFormattedPrice()}"
        } else {
            total.toFormattedPrice()
        }

        composeTestRule
            .onNodeWithText(cartTotal, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun navigateToCheckout() = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.checkout_button_text), useUnmergedTree = true)
            .performClick()
    }

}