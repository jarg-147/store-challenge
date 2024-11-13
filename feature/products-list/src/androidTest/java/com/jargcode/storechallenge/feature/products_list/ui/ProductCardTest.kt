package com.jargcode.storechallenge.feature.products_list.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.testing.discounts.fixedPriceDiscount
import com.jargcode.storechallenge.core.testing.discounts.freeDiscount
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.products_list.data.productUi
import org.junit.Rule
import org.junit.Test

class ProductCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenProductWithFreeItemDiscount_whenDisplayed_thenDisplayFreeItemDiscountInfo() {
        val freeItemDiscount = freeDiscount(
            productCode = "VOUCHER",
            minQuantity = 2
        )

        val product = productUi(
            discount = freeItemDiscount
        )

        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = product,
                    onAddToCartClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText("Discount", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Buy 2 and get 1 free", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenProductWithFixedPriceDiscount_whenDisplayed_thenDisplayFixedPriceDiscountInfo() {
        val fixedPriceDiscount = fixedPriceDiscount(
            productCode = "VOUCHER",
            minQuantity = 2,
            fixedPrice = 4.0
        )

        val product = productUi(
            discount = fixedPriceDiscount
        )

        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = product,
                    onAddToCartClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText("Discount", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("When buying 2 or more, ${4.00.toFormattedPrice()} each", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenProductWithoutDiscount_whenDisplayed_thenDoNotDisplayDiscountInfo() {
        val product = productUi()

        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = product,
                    onAddToCartClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText("Discount", useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeRule
            .onNode(hasText("When buying"), useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeRule
            .onNode(hasText("and get 1 free"), useUnmergedTree = true)
            .assertIsNotDisplayed()
    }

}