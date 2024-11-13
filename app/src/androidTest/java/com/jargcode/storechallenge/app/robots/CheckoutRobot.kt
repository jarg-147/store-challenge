package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.R

class CheckoutRobot(
    private val composeTestRule: StoreComposeRule,
) {
    fun assertProductIsInSummary(
        productName: String,
        productDescription: String,
        productTotal: Double,
    ) = apply {
        composeTestRule
            .onNodeWithText(productName, useUnmergedTree = true)
            .assertIsDisplayed()
            .onSiblings()
            .assertAny(hasTextExactly(productTotal.toFormattedPrice()))

        composeTestRule
            .onNodeWithText(productName, useUnmergedTree = true)
            .assertIsDisplayed()
            .onParent()
            .onChildren()
            .assertAny(hasTextExactly(productDescription))
    }

    fun assertNoSubtotal() = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.checkout_summary_subtotal_section), useUnmergedTree = true)
            .assertDoesNotExist()
    }

    fun assertSubtotal(subtotal: Double) = apply {
        composeTestRule
            .onNodeWithText(subtotal.toFormattedPrice(), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertNoDiscounts() = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.checkout_summary_discount_section), useUnmergedTree = true)
            .assertDoesNotExist()
    }

    fun assertDiscounts(discounts: List<String>) = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.checkout_summary_discount_section), useUnmergedTree = true)
            .assertIsDisplayed()

        discounts.forEach { discountPart ->
            composeTestRule
                .onAllNodes(hasText(discountPart, substring = true))
        }
    }

    fun assertTotal(total: Double) = apply {
        composeTestRule
            .onNodeWithText(total.toFormattedPrice(), useUnmergedTree = true)
            .assertIsDisplayed()
    }


}