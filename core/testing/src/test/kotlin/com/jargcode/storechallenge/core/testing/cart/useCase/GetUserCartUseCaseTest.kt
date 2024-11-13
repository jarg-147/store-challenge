package com.jargcode.storechallenge.core.testing.cart.useCase

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.*
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.cart.*
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.discounts.discounts
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.products
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUserCartUseCaseTest {

    private lateinit var cartRepository: FakeCartRepository
    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository

    private lateinit var getProductsListUseCase: GetProductsListUseCase
    private lateinit var useCase: GetUserCartUseCase

    @Before
    fun setUp() {
        cartRepository = FakeCartRepository()
        productsRepository = FakeProductsRepository()
        discountsRepository = FakeDiscountsRepository()

        getProductsListUseCase = GetProductsListUseCase(
            productsRepository = productsRepository,
            discountsRepository = discountsRepository
        )

        useCase = GetUserCartUseCase(
            getProductsListUseCase = getProductsListUseCase,
            cartRepository = cartRepository
        )
    }

    @Test
    fun `given cart is empty, when invoke, then expect empty cart`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()

            discountsRepository.setDiscounts(fakeDiscounts)
            awaitItem() // Ignore first empty emission
            productsRepository.setProducts(fakeProducts)
            awaitItem() // Ignore second empty emission

            // THEN
            val cartProducts = awaitItem()
            assertThat(cartProducts.cartProducts).isEmpty()
            assertThat(cartProducts.totalPriceWithoutDiscounts).isEqualTo(0.0)
        }
    }

    @Test
    fun `given cart has an item without discount, when invoke, then expect item without discount`() = runTest {
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
            val cartProducts = awaitItem()
            assertThat(cartProducts.cartProducts).hasSize(1)
            assertThat(cartProducts.totalPriceWithoutDiscounts).isEqualTo(7.5)
        }
    }

    @Test
    fun `given cart has an item with discount, when invoke, then expect item with discount`() = runTest {
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
            val cartProducts = awaitItem()
            assertThat(cartProducts.cartProducts).hasSize(1)
            assertThat(cartProducts.cartProducts.first().product.discount).isNotNull()
            assertThat(cartProducts.totalPriceWithoutDiscounts).isEqualTo(5.0)
        }
    }

}