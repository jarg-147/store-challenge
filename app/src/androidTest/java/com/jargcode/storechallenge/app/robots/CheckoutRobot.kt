package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule

class CheckoutRobot(
    private val composeTestRule: StoreComposeRule,
) {
    fun assertProductIsInSummary(
        productName: String,
        productDescription: String,
        productTotal: String,
    ) = apply {
        composeTestRule
            .onNodeWithText(productName, useUnmergedTree = true)
            .assertIsDisplayed()
            .onSiblings()
            .assertAny(hasTextExactly(productTotal))

        composeTestRule
            .onNodeWithText(productName, useUnmergedTree = true)
            .assertIsDisplayed()
            .onParent()
            .onChildren()
            .assertAny(hasTextExactly(productDescription))
    }

    fun assertNoSubtotal() = apply {
        composeTestRule
            .onNodeWithText("Subtotal", useUnmergedTree = true)
            .assertDoesNotExist()
    }

    fun assertSubtotal(subtotal: String) = apply {
        composeTestRule
            .onNodeWithText(subtotal, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertNoDiscounts() = apply {
        composeTestRule
            .onNodeWithText("Discounts", useUnmergedTree = true)
            .assertDoesNotExist()
    }

    fun assertDiscounts(discounts: List<String>) = apply {
        composeTestRule
            .onNodeWithText("Discounts", useUnmergedTree = true)
            .assertIsDisplayed()

        discounts.forEach { discountPart ->
            composeTestRule
                .onAllNodes(hasText(discountPart, substring = true))
        }
    }

    fun assertTotal(total: String) = apply {
        composeTestRule
            .onNodeWithText(total, useUnmergedTree = true)
            .assertIsDisplayed()
    }


}