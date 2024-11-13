package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.core.designsystem.R as DSRes

class CartRobot(
    private val composeTestRule: StoreComposeRule,
) {

    @OptIn(ExperimentalTestApi::class)
    fun waitUntilLoadingFinished() = apply {
        val loadingContentDesc = composeTestRule.activity.getString(DSRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc))
    }

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