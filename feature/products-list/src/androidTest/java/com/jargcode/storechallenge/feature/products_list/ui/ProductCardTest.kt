package com.jargcode.storechallenge.feature.products_list.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.feature.products_list.model.ProductUi
import org.junit.Rule
import org.junit.Test

class ProductCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenProductWithFreeItemDiscount_whenDisplayed_thenDisplayDiscountInfo() {
        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = ProductUi.mock,
                    onAddToCartClick = {}
                )
            }
        }

        composeRule.onNodeWithText("Discount", useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Buy 2, get 1 free", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun givenProductWithFixedPriceDiscount_whenDisplayed_thenDisplayDiscountInfo() {
        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = ProductUi.mock.copy(
                        discountText = StringWrapper(
                            text = "When buying 3 or more, 19.00€ each"
                        )
                    ),
                    onAddToCartClick = {}
                )
            }
        }

        composeRule.onNodeWithText("Discount", useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("When buying 3 or more, 19.00€ each", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun givenProductWithNoDiscount_whenDisplayed_thenDoNotDisplayDiscountInfo() {
        composeRule.setContent {
            StoreTheme {
                ProductCard(
                    modifier = Modifier.fillMaxWidth(),
                    product = ProductUi.mock.copy(
                        discountText = null
                    ),
                    onAddToCartClick = {}
                )
            }
        }

        composeRule.onNodeWithText("Discount", useUnmergedTree = true).assertIsNotDisplayed()
        composeRule.onNodeWithText("When buying 3 or more, 19.00€ each", useUnmergedTree = true).assertIsNotDisplayed()
    }

}