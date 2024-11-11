package com.jargcode.storechallenge.feature.checkout

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.data.*
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState
import org.junit.*

class CheckoutScreenTest {

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
            CheckoutScreen(
                uiState = CheckoutUiState.Loading,
                onUiEvent = {},
            )
        }

        val loadingContentDescription = context.getString(com.jargcode.storechallenge.core.designsystem.R.string.loading_indicator_content_description)

        composeTestRule
            .onNodeWithContentDescription(loadingContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateIsError_thenDisplayErrorView() {
        composeTestRule.setContent {
            CheckoutScreen(
                uiState = CheckoutUiState.Error,
                onUiEvent = {}
            )
        }

        val errorViewTitle = context.getString(R.string.checkout_summary_error_title)
        val errorViewText = context.getString(R.string.checkout_summary_error_text)
        val retryButtonText = context.getString(R.string.checkout_summary_error_button)

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
    fun givenUiStateIsSuccess_whenThereAreNoDiscounts_thenDisplayOnlyProducts() {
        val checkoutProducts = mugCheckoutUi()

        composeTestRule.setContent {
            CheckoutScreen(
                uiState = CheckoutUiState.Success(
                    products = checkoutProducts,
                    subtotal = 7.5.toFormattedPrice(),
                    appliedDiscounts = emptyList(),
                    total = 7.5.toFormattedPrice()
                ),
                onUiEvent = {}
            )
        }

        val checkoutSummaryContentTestTag = context.getString(R.string.checkout_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(checkoutSummaryContentTestTag, useUnmergedTree = true)
            .onChildAt(0)

        val productsHeader = context.getString(R.string.checkout_summary_products_section)
        lazyColumn
            .performScrollToNode(hasTextExactly(productsHeader))
            .assertIsDisplayed()

        lazyColumn
            .performScrollToNode(hasTextExactly(checkoutProducts.first().name))
            .assertIsDisplayed()

        val discountsHeader = context.getString(R.string.checkout_summary_discount_section)
        composeTestRule
            .onNode(hasTextExactly(discountsHeader))
            .assertDoesNotExist()
    }

    @Test
    fun givenUiStateIsSuccess_whenFreeItemDiscountApplied_thenDisplayProductsAndDiscounts() {
        val checkoutProducts = voucherCheckoutUi(quantity = 2)

        composeTestRule.setContent {
            CheckoutScreen(
                uiState = CheckoutUiState.Success(
                    products = checkoutProducts,
                    subtotal = 10.0.toFormattedPrice(),
                    appliedDiscounts = appliedFreeItemDiscount(
                        quantity = 1,
                        productName = checkoutProducts.first().name,
                        totalDiscounted = 5.0,
                        timesApplied = 1
                    ),
                    total = 5.0.toFormattedPrice()
                ),
                onUiEvent = {}
            )
        }

        val checkoutSummaryContentTestTag = context.getString(R.string.checkout_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(checkoutSummaryContentTestTag, useUnmergedTree = true)
            .onChildAt(0)

        val productsHeader = context.getString(R.string.checkout_summary_products_section)
        lazyColumn
            .performScrollToNode(hasTextExactly(productsHeader))
            .assertIsDisplayed()

        lazyColumn
            .performScrollToNode(hasTextExactly(checkoutProducts.first().name))
            .assertIsDisplayed()

        val discountsHeader = context.getString(R.string.checkout_summary_discount_section)
        composeTestRule
            .onNode(hasTextExactly(discountsHeader))
            .assertIsDisplayed()

        val freeItemDiscountText = context.getString(R.string.buy_and_get_free_discount_info, 1, checkoutProducts.first().name)
        lazyColumn
            .performScrollToNode(hasTextExactly(freeItemDiscountText))
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateIsSuccess_whenFixedPriceDiscountApplied_thenDisplayProductsAndDiscounts() {
        val checkoutProducts = tShirtCheckoutUi(quantity = 3)

        composeTestRule.setContent {
            CheckoutScreen(
                uiState = CheckoutUiState.Success(
                    products = checkoutProducts,
                    subtotal = 60.0.toFormattedPrice(),
                    appliedDiscounts = appliedFixedPriceDiscount(
                        productName = checkoutProducts.first().name,
                        totalDiscounted = 3.0,
                        timesApplied = 3
                    ),
                    total = 57.00.toFormattedPrice()
                ),
                onUiEvent = {}
            )
        }

        val checkoutSummaryContentTestTag = context.getString(R.string.checkout_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(checkoutSummaryContentTestTag, useUnmergedTree = true)
            .onChildAt(0)

        val productsHeader = context.getString(R.string.checkout_summary_products_section)
        lazyColumn
            .performScrollToNode(hasTextExactly(productsHeader))
            .assertIsDisplayed()

        lazyColumn
            .performScrollToNode(hasTextExactly(checkoutProducts.first().name))
            .assertIsDisplayed()

        val discountsHeader = context.getString(R.string.checkout_summary_discount_section)
        composeTestRule
            .onNode(hasTextExactly(discountsHeader))
            .assertIsDisplayed()

        val fixedPriceDiscountText = context.getString(R.string.reduced_price_discount_info, checkoutProducts.first().name)
        lazyColumn
            .performScrollToNode(hasTextExactly(fixedPriceDiscountText))
            .assertIsDisplayed()
    }

}