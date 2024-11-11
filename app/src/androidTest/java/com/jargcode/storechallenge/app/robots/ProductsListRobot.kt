package com.jargcode.storechallenge.app.robots

import androidx.compose.ui.test.*
import com.jargcode.storechallenge.app.StoreComposeRule

class ProductsListRobot(
    private val composeTestRule: StoreComposeRule,
) {

    fun addVoucherToCart(): ProductsListRobot = apply {
        composeTestRule
            .onAllNodesWithText("Add to cart", useUnmergedTree = true)
            .onFirst()
            .performScrollTo()
            .performClick()
    }


    fun addTShirtToCart(): ProductsListRobot = apply {
        composeTestRule
            .onAllNodesWithText("Add to cart", useUnmergedTree = true)[1]
            .performScrollTo()
            .performClick()
    }

    fun addCoffeeMugToCart(): ProductsListRobot = apply {
        composeTestRule
            .onAllNodesWithText("Add to cart", useUnmergedTree = true)
            .onLast()
            .performScrollTo()
            .performClick()
    }

}