package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule
import com.jargcode.storechallenge.feature.products_list.R

@OptIn(ExperimentalTestApi::class)
class ProductsListRobot(
    private val composeTestRule: StoreComposeRule,
) {

    fun addVoucherToCart(): ProductsListRobot = apply {
        composeTestRule
            .onNodeWithTag(composeTestRule.activity.getString(R.string.product_list_content_test_tag))
            .performScrollToNode(hasTextExactly("Cabify Voucher"))

        composeTestRule
            .onNodeWithText("Cabify Voucher", useUnmergedTree = true)
            .onSiblings()
            .filter(hasClickAction())
            .onFirst()
            .performClick()

        composeTestRule
            .waitUntilAtLeastOneExists(hasTextExactly(composeTestRule.activity.getString(R.string.snackbar_product_added_to_cart_success)))
    }

    fun addTShirtToCart(): ProductsListRobot = apply {
        composeTestRule
            .onNodeWithTag(composeTestRule.activity.getString(R.string.product_list_content_test_tag))
            .performScrollToNode(hasTextExactly("Cabify T-Shirt"))

        composeTestRule
            .onNodeWithText("Cabify T-Shirt", useUnmergedTree = true)
            .onSiblings()
            .filter(hasClickAction())
            .onFirst()
            .performClick()

        composeTestRule
            .waitUntilAtLeastOneExists(hasTextExactly(composeTestRule.activity.getString(R.string.snackbar_product_added_to_cart_success)))
    }

    fun addCoffeeMugToCart(): ProductsListRobot = apply {
        composeTestRule
            .onNodeWithTag(composeTestRule.activity.getString(R.string.product_list_content_test_tag))
            .performScrollToNode(hasTextExactly("Cabify Coffee Mug"))

        composeTestRule
            .onNodeWithText("Cabify Coffee Mug", useUnmergedTree = true)
            .onSiblings()
            .filter(hasClickAction())
            .onFirst()
            .performClick()

        composeTestRule
            .waitUntilAtLeastOneExists(hasTextExactly(composeTestRule.activity.getString(R.string.snackbar_product_added_to_cart_success)))

    }

    fun dismissSnackbar(): ProductsListRobot = apply {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.snackbar_product_added_to_cart_success), useUnmergedTree = true)
            .onSiblings()
            .filter(hasClickAction())
            .onFirst()
            .performClick()
    }

}