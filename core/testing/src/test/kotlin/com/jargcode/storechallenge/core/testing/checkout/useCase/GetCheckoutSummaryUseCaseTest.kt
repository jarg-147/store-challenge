package com.jargcode.storechallenge.core.testing.checkout.useCase

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.*
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.core.domain.checkout.useCase.GetCheckoutSummaryUseCase
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.cart.*
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.discounts.discounts
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.products
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCheckoutSummaryUseCaseTest {

    private lateinit var cartRepository: FakeCartRepository
    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository

    private lateinit var getProductsListUseCase: GetProductsListUseCase
    private lateinit var getUserCartUseCase: GetUserCartUseCase
    private lateinit var useCase: GetCheckoutSummaryUseCase

    @Before
    fun setUp() {
        cartRepository = FakeCartRepository()
        productsRepository = FakeProductsRepository()
        discountsRepository = FakeDiscountsRepository()

        getProductsListUseCase = GetProductsListUseCase(
            productsRepository = productsRepository,
            discountsRepository = discountsRepository
        )

        getUserCartUseCase = GetUserCartUseCase(
            getProductsListUseCase = getProductsListUseCase,
            cartRepository = cartRepository
        )

        useCase = GetCheckoutSummaryUseCase(
            getUserCartUseCase = getUserCartUseCase
        )
    }

    @Test
    fun `given checkout has an item without discount, when invoke, then expect summary without discount`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = mugCart()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(1)
            assertThat(summary.subtotal).isEqualTo(7.5)
            assertThat(summary.appliedDiscounts).isEmpty()
            assertThat(summary.total).isEqualTo(7.5)
        }
    }

    @Test
    fun `given checkout has an item with non applicable discount, when invoke, then expect summary without discount`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = voucherCart()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(1)
            assertThat(summary.subtotal).isEqualTo(5.0)
            assertThat(summary.appliedDiscounts).isEmpty()
            assertThat(summary.total).isEqualTo(5.0)
        }
    }

    @Test
    fun `given checkout has an item with applicable discount, when invoke, then expect summary with applied discount`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = voucherCart(quantity = 2)

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(1)
            assertThat(summary.checkoutProducts.first().quantity).isEqualTo(2)
            assertThat(summary.subtotal).isEqualTo(10.0)
            assertThat(summary.appliedDiscounts).isNotEmpty()

            val appliedDiscount = summary.appliedDiscounts.first()
            assertThat(appliedDiscount.discount).isInstanceOf(FreeItem::class)
            assertThat(appliedDiscount.timesApplied).isEqualTo(1)
            assertThat(appliedDiscount.totalDiscounted).isEqualTo(5.0)
            assertThat(summary.total).isEqualTo(5.0)
        }
    }

    // region Challenge tests

    @Test
    fun `given example1, when invoke, then expect example1 total price`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = example1()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(3)
            assertThat(summary.appliedDiscounts).isEmpty()
            assertThat(summary.total).isEqualTo(32.50)
        }
    }

    @Test
    fun `given example2, when invoke, then expect example2 total price`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = example2()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(2)
            assertThat(summary.appliedDiscounts).hasSize(1)
            assertThat(summary.total).isEqualTo(25.00)
        }
    }

    @Test
    fun `given example3, when invoke, then expect example3 total price`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = example3()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(2)
            assertThat(summary.appliedDiscounts).hasSize(1)
            assertThat(summary.total).isEqualTo(81.00)
        }
    }

    @Test
    fun `given example4, when invoke, then expect example4 total price`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()
            val fakeCart = example4()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission
            cartRepository.setCartProducts(fakeCart)
            awaitItem() // Ignore third empty emission

            // THEN
            val summary = awaitItem()
            assertThat(summary.checkoutProducts).hasSize(3)
            assertThat(summary.appliedDiscounts).hasSize(2)
            assertThat(summary.total).isEqualTo(74.50)
        }
    }


    // endregion Challenge tests

}