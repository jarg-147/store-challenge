package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.R
import com.jargcode.storechallenge.core.designsystem.R as DSRes

class CheckoutRobot(
    private val composeTestRule: StoreComposeRule,
) {

    @OptIn(ExperimentalTestApi::class)
    fun waitUntilLoadingFinished() = apply {
        val loadingContentDesc = composeTestRule.activity.getString(DSRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc))
    }

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