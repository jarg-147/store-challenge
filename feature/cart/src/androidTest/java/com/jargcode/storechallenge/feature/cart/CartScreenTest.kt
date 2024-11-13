package com.jargcode.storechallenge.feature.cart

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.data.cartProducts
import com.jargcode.storechallenge.feature.cart.data.cartProductsUi
import com.jargcode.storechallenge.feature.cart.model.CartUiState
import org.junit.*
import com.jargcode.storechallenge.core.designsystem.R as DesignSystem

class CartScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: ComponentActivity

    @Before
    fun setUp() {
        context = composeTestRule.activity
    }

    @Test
    fun givenUiStateIsLoading_thenDisplayLoadingIndicator() {
        composeTestRule.setContent {
            CartScreen(
                uiState = CartUiState.Loading,
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val loadingContentDescription = context.getString(DesignSystem.string.loading_indicator_content_description)

        composeTestRule
            .onNodeWithContentDescription(loadingContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateIsError_thenDisplayErrorView() {
        composeTestRule.setContent {
            CartScreen(
                uiState = CartUiState.Error,
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val errorViewTitle = context.getString(R.string.cart_error_title)
        val errorViewText = context.getString(R.string.cart_error_text)
        val retryButtonText = context.getString(R.string.cart_error_button)

        composeTestRule
            .onNodeWithText(errorViewTitle, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(errorViewText, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(retryButtonText, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateIsSuccess_whenHasProducts_thenDisplaysProducts() {
        val products = cartProductsUi()
        val totalPrice = cartProducts().sumOf { it.total }

        composeTestRule.setContent {
            CartScreen(
                uiState = CartUiState.Success(
                    cartProducts = products,
                    totalPriceWithoutDiscounts = totalPrice.toFormattedPrice()
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val cartContentTestTag = context.getString(R.string.cart_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(cartContentTestTag, useUnmergedTree = true)
            .onChildAt(0)

        products.forEach { productUi ->
            lazyColumn
                .performScrollToNode(hasTextExactly(productUi.name))
                .assertIsDisplayed()

            lazyColumn
                .performScrollToNode(hasTextExactly("${productUi.price} / u"))
                .assertIsDisplayed()
        }

        val totalContentDescription = context.getString(R.string.total_price_content_description)
        composeTestRule
            .onNodeWithContentDescription(totalContentDescription, useUnmergedTree = true)
            .assertTextEquals(totalPrice.toFormattedPrice())
    }

    @Test
    fun givenUiStateIsSuccess_whenHasProductsWithDiscounts_thenDisplaysProductsWithDiscounts() {
        val products = cartProductsUi(
            voucherWithDiscount = true,
            tShirtWithDiscount = true
        )
        val totalPrice = cartProducts(
            voucherWithDiscount = true,
            tShirtWithDiscount = true
        ).sumOf { it.total }

        composeTestRule.setContent {
            CartScreen(
                uiState = CartUiState.Success(
                    cartProducts = products,
                    totalPriceWithoutDiscounts = totalPrice.toFormattedPrice()
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val cartContentTestTag = context.getString(R.string.cart_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(cartContentTestTag, useUnmergedTree = true)
            .onChildAt(0)

        products.forEach { productUi ->
            lazyColumn
                .performScrollToNode(hasTextExactly(productUi.name))
                .assertIsDisplayed()

            if (productUi.discountInfo != null) {
                lazyColumn
                    .performScrollToNode(hasTextExactly("*${productUi.price} / u"))
                    .assertIsDisplayed()
            }
        }

        val discountsDisclaimer = context.getString(R.string.checkout_discounts_disclaimer)
        lazyColumn
            .onChildren()
            .onLast()
            .assertTextEquals(discountsDisclaimer)

        val totalContentDescription = context.getString(R.string.total_price_content_description)
        val totalWithDiscountsText = context.getString(R.string.total_price_value_with_discounts, totalPrice.toFormattedPrice())
        composeTestRule
            .onNodeWithContentDescription(totalContentDescription, useUnmergedTree = true)
            .assertTextEquals(totalWithDiscountsText)
    }

    @Test
    fun givenUiStateIsSuccess_whenCartIsEmpty_thenDisplaysEmptyView() {
        composeTestRule.setContent {
            CartScreen(
                uiState = CartUiState.Success(
                    cartProducts = emptyList(),
                    totalPriceWithoutDiscounts = 0.00.toFormattedPrice()
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val emptyTitle = context.getString(R.string.cart_empty_title)
        val emptyText = context.getString(R.string.cart_empty_text)

        composeTestRule
            .onNodeWithText(emptyTitle, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(emptyText, useUnmergedTree = true)
            .assertIsDisplayed()
    }

}