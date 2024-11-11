package com.jargcode.storechallenge.app.purchase

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jargcode.storechallenge.app.MainActivity
import com.jargcode.storechallenge.app.robots.*
import com.jargcode.storechallenge.core.testing.StoreAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import com.jargcode.storechallenge.core.designsystem.R as DesignRes

@HiltAndroidTest
class CreatePurchaseExamplesE2E : StoreAndroidTest() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val productsListRobot = ProductsListRobot(composeTestRule)
    private val cartRobot = CartRobot(composeTestRule)
    private val checkoutListRobot = CheckoutRobot(composeTestRule)
    private val bottomBarRobot = BottomBarRobot(composeTestRule)

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun whenPurchasingExample1_thenAssertExample1CheckoutSummary() {
        val loadingContentDesc = composeTestRule.activity.getString(DesignRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc), timeoutMillis = 25000L)

        productsListRobot
            .addVoucherToCart()
            .addTShirtToCart()
            .addCoffeeMugToCart()

        bottomBarRobot
            .assertBadgeIsDisplayed("3")
            .navigateToCart()

        cartRobot
            .assertProductIsInCart(productName = "Cabify Voucher", productQuantity = "x 1")
            .assertProductIsInCart(productName = "Cabify T-Shirt", productQuantity = "x 1")
            .assertProductIsInCart(productName = "Cabify Coffee Mug", productQuantity = "x 1")
            .assertTotalPrice("32.50€")
            .navigateToCheckout()

        checkoutListRobot
            .assertProductIsInSummary(productName = "Cabify Voucher", productDescription = "x 1 - 5.00€ / u", productTotal = "5.00€")
            .assertProductIsInSummary(productName = "Cabify T-Shirt", productDescription = "x 1 - 20.00€ / u", productTotal = "20.00€")
            .assertProductIsInSummary(productName = "Cabify Coffee Mug", productDescription = "x 1 - 7.50€ / u", productTotal = "7.50€")
            .assertNoSubtotal()
            .assertNoDiscounts()
            .assertTotal("32.50€")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun whenPurchasingExample2_thenAssertExample2CheckoutSummary() {
        val loadingContentDesc = composeTestRule.activity.getString(DesignRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc), timeoutMillis = 25000L)

        productsListRobot
            .addVoucherToCart()
            .addTShirtToCart()
            .addVoucherToCart()

        bottomBarRobot
            .assertBadgeIsDisplayed("3")
            .navigateToCart()

        cartRobot
            .assertProductIsInCart(productName = "Cabify Voucher", productQuantity = "x 2")
            .assertProductIsInCart(productName = "Cabify T-Shirt", productQuantity = "x 1")
            .assertTotalPrice("*30.00€")
            .navigateToCheckout()

        checkoutListRobot
            .assertProductIsInSummary(productName = "Cabify Voucher", productDescription = "x 2 - 5.00€ / u", productTotal = "10.00€")
            .assertProductIsInSummary(productName = "Cabify T-Shirt", productDescription = "x 1 - 20.00€ / u", productTotal = "20.00€")
            .assertSubtotal("30.00€")
            .assertDiscounts(listOf("get 1 free"))
            .assertTotal("25.00€")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun whenPurchasingExample3_thenAssertExample3CheckoutSummary() {
        val loadingContentDesc = composeTestRule.activity.getString(DesignRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc), timeoutMillis = 25000L)

        productsListRobot
            .addTShirtToCart()
            .addTShirtToCart()
            .addTShirtToCart()
            .addVoucherToCart()
            .addTShirtToCart()

        bottomBarRobot
            .assertBadgeIsDisplayed("5")
            .navigateToCart()

        cartRobot
            .assertProductIsInCart(productName = "Cabify Voucher", productQuantity = "x 1")
            .assertProductIsInCart(productName = "Cabify T-Shirt", productQuantity = "x 4")
            .assertTotalPrice("*85.00€")
            .navigateToCheckout()

        checkoutListRobot
            .assertProductIsInSummary(productName = "Cabify Voucher", productDescription = "x 1 - 5.00€ / u", productTotal = "5.00€")
            .assertProductIsInSummary(productName = "Cabify T-Shirt", productDescription = "x 4 - 20.00€ / u", productTotal = "80.00€")
            .assertSubtotal("85.00€")
            .assertDiscounts(listOf("reduced price"))
            .assertTotal("81.00€")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun whenPurchasingExample4_thenAssertExample4CheckoutSummary() {
        val loadingContentDesc = composeTestRule.activity.getString(DesignRes.string.loading_indicator_content_description)
        composeTestRule.waitUntilDoesNotExist(hasContentDescriptionExactly(loadingContentDesc), timeoutMillis = 25000L)

        productsListRobot
            .addVoucherToCart()
            .addTShirtToCart()
            .addVoucherToCart()
            .addVoucherToCart()
            .addCoffeeMugToCart()
            .addTShirtToCart()
            .addTShirtToCart()

        bottomBarRobot
            .assertBadgeIsDisplayed("7")
            .navigateToCart()

        cartRobot
            .assertProductIsInCart(productName = "Cabify Voucher", productQuantity = "x 3")
            .assertProductIsInCart(productName = "Cabify T-Shirt", productQuantity = "x 3")
            .assertProductIsInCart(productName = "Cabify Coffee Mug", productQuantity = "x 1")
            .assertTotalPrice("*82.50€")
            .navigateToCheckout()

        checkoutListRobot
            .assertProductIsInSummary(productName = "Cabify Voucher", productDescription = "x 3 - 5.00€ / u", productTotal = "15.00€")
            .assertProductIsInSummary(productName = "Cabify T-Shirt", productDescription = "x 3 - 20.00€ / u", productTotal = "60.00€")
            .assertProductIsInSummary(productName = "Cabify Coffee Mug", productDescription = "x 1 - 7.50€ / u", productTotal = "7.50€")
            .assertSubtotal("82.50€")
            .assertDiscounts(listOf("get 1 free", "reduced price"))
            .assertTotal("74.50€")
    }

}