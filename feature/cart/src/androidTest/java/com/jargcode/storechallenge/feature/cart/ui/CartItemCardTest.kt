package com.jargcode.storechallenge.feature.cart.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.feature.cart.data.cartProductUi
import org.junit.Rule
import org.junit.Test

class CartItemCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenCartProductWithoutDiscount_whenDisplayed_thenDoNotDisplayDiscountInfo() {
        val fakeProduct = cartProductUi()

        composeRule.setContent {
            StoreTheme {
                CartProductCart(
                    modifier = Modifier.fillMaxWidth(),
                    product = fakeProduct,
                    onDeleteProductClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText(fakeProduct.name, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("${fakeProduct.price} / u", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("x ${fakeProduct.quantity}", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNode(hasText("Discount"))
            .assertIsNotDisplayed()
    }

    @Test
    fun givenCartProductWithFreeDiscount_whenQuantityNotReached_thenDisplayQuantityLeftToDiscount() {
        val fakeProduct = cartProductUi(
            discount = FreeItem(
                productCode = "VOUCHER",
                minQuantity = 2
            )
        )

        composeRule.setContent {
            StoreTheme {
                CartProductCart(
                    modifier = Modifier.fillMaxWidth(),
                    product = fakeProduct,
                    onDeleteProductClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText(fakeProduct.name, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("${fakeProduct.price} / u", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("x ${fakeProduct.quantity}", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNode(hasTextExactly("Buy 1 more and get 1 free!"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenCartProductWithFixedDiscount_whenQuantityNotReached_thenDisplayQuantityLeftToDiscount() {
        val fakeProduct = cartProductUi(
            discount = FixedPrice(
                productCode = "VOUCHER",
                minQuantity = 2,
                fixedPrice = 4.0
            )
        )

        composeRule.setContent {
            StoreTheme {
                CartProductCart(
                    modifier = Modifier.fillMaxWidth(),
                    product = fakeProduct,
                    onDeleteProductClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText(fakeProduct.name, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("${fakeProduct.price} / u", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("x ${fakeProduct.quantity}", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNode(hasTextExactly("Buy 1 more and get each one at 4.00€!"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenCartProductWithFreeDiscount_whenQuantityReached_thenDisplayDiscountReached() {
        val fakeProduct = cartProductUi(
            discount = FreeItem(
                productCode = "VOUCHER",
                minQuantity = 1
            )
        )

        composeRule.setContent {
            StoreTheme {
                CartProductCart(
                    modifier = Modifier.fillMaxWidth(),
                    product = fakeProduct,
                    onDeleteProductClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText(fakeProduct.name, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("*${fakeProduct.price} / u", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("x ${fakeProduct.quantity}", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNode(hasTextExactly("Discount eligible at checkout!"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenCartProductWithFixedDiscount_whenQuantityReached_thenDisplayDiscountReached() {
        val fakeProduct = cartProductUi(
            discount = FixedPrice(
                productCode = "VOUCHER",
                minQuantity = 1,
                fixedPrice = 4.0
            )
        )

        composeRule.setContent {
            StoreTheme {
                CartProductCart(
                    modifier = Modifier.fillMaxWidth(),
                    product = fakeProduct,
                    onDeleteProductClick = {}
                )
            }
        }

        composeRule
            .onNodeWithText(fakeProduct.name, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("*${fakeProduct.price} / u", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("x ${fakeProduct.quantity}", useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNode(hasTextExactly("Discount eligible at checkout!"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

}