package com.jargcode.storechallenge.core.testing.products.useCase

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.discounts.discounts
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.products
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetProductsListUseCaseTest {

    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository
    private lateinit var useCase: GetProductsListUseCase

    @Before
    fun setUp() {
        productsRepository = FakeProductsRepository()
        discountsRepository = FakeDiscountsRepository()
        useCase = GetProductsListUseCase(
            productsRepository = productsRepository,
            discountsRepository = discountsRepository
        )
    }

    @Test
    fun `given there are discounts, when invoke, then assert products have expected discounts`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscounts = discounts()

            discountsRepository.setDiscounts(fakeDiscounts)
            productsRepository.setProducts(fakeProducts)

            // THEN
            awaitItem() // Ignore first empty emission
            awaitItem() // Ignore second empty emission

            val products = awaitItem()
            val productsWithDiscounts = products.filter { it.discount != null }

            assertThat(products.size).isEqualTo(fakeProducts.size)
            assertThat(productsWithDiscounts.size).isEqualTo(2)
        }
    }

    @Test
    fun `given there are not discounts, when invoke, then products do not have discounts`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()

            awaitItem() // Ignore first empty emission

            productsRepository.setProducts(fakeProducts)

            // THEN
            val products = awaitItem()
            val productsWithDiscounts = products.filter { it.discount != null }

            assertThat(products.size).isEqualTo(fakeProducts.size)
            assertThat(productsWithDiscounts.size).isEqualTo(0)
        }
    }

    @Test
    fun `given there are unknown discounts, when invoke, then products do not have discounts`() = runTest {
        useCase().test {
            // GIVEN
            val fakeProducts = products()
            val fakeDiscount = listOf(FreeItem(productCode = "1", minQuantity = 2))

            discountsRepository.setDiscounts(fakeDiscount)
            productsRepository.setProducts(fakeProducts)

            awaitItem() // Ignore first empty emission
            awaitItem() // Ignore second empty emission

            // THEN
            val products = awaitItem()
            val productsWithDiscounts = products.filter { it.discount != null }

            assertThat(products.size).isEqualTo(fakeProducts.size)
            assertThat(productsWithDiscounts.size).isEqualTo(0)
        }
    }

}