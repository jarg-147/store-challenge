package com.jargcode.storechallenge.feature.checkout

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.*
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.core.domain.checkout.useCase.GetCheckoutSummaryUseCase
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.cart.FakeCartRepository
import com.jargcode.storechallenge.core.testing.cart.voucherCart
import com.jargcode.storechallenge.core.testing.coroutines.MainCoroutineRule
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.discounts.discounts
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.products
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState
import kotlinx.coroutines.test.runTest
import org.junit.*

class CheckoutViewModelTest {

    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository
    private lateinit var cartRepository: FakeCartRepository

    private lateinit var getProductsListUseCase: GetProductsListUseCase
    private lateinit var getUserCartUseCase: GetUserCartUseCase
    private lateinit var getCheckoutSummaryUseCase: GetCheckoutSummaryUseCase

    private lateinit var viewModel: CheckoutViewModel

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Before
    fun setUp() {
        productsRepository = FakeProductsRepository()
        discountsRepository = FakeDiscountsRepository()
        cartRepository = FakeCartRepository()

        getProductsListUseCase = GetProductsListUseCase(
            productsRepository = productsRepository,
            discountsRepository = discountsRepository
        )

        getUserCartUseCase = GetUserCartUseCase(
            getProductsListUseCase = getProductsListUseCase,
            cartRepository = cartRepository
        )

        getCheckoutSummaryUseCase = GetCheckoutSummaryUseCase(
            getUserCartUseCase = getUserCartUseCase
        )

        viewModel = CheckoutViewModel(
            getCheckoutSummaryUseCase = getCheckoutSummaryUseCase
        )
    }

    @Test
    fun `given user is in checkout, when viewmodel init is called, then checkout summary is loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val productsList = products()
            val discountsList = discounts()
            val cartProducts = voucherCart()

            productsRepository.setProducts(productsList)
            discountsRepository.setDiscounts(discountsList)
            cartRepository.setCartProducts(cartProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CheckoutUiState.Loading)

            // WHEN
            viewModel.init()

            // THEN
            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CheckoutUiState.Success::class)
            successEmission as CheckoutUiState.Success

            assertThat(successEmission.products.size).isEqualTo(cartProducts.size)
            assertThat(successEmission.subtotal).isEqualTo(5.00.toFormattedPrice())
            assertThat(successEmission.appliedDiscounts).isEmpty()
            assertThat(successEmission.total).isEqualTo(5.00.toFormattedPrice())
        }
    }

    @Test
    fun `given user is in products list, when viewmodel init returns error, then error is displayed`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val productsList = products()
            productsRepository.setProducts(productsList)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CheckoutUiState.Loading)

            // WHEN
            productsRepository.getProductsThrowError = true
            viewModel.init()

            // THEN
            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(CheckoutUiState.Error)
        }
    }

    @Test
    fun `given state is error, when user clicks retry, checkout summary is loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val productsList = products()
            productsRepository.setProducts(productsList)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CheckoutUiState.Loading)

            productsRepository.getProductsThrowError = true
            viewModel.init()

            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(CheckoutUiState.Error)

            // WHEN
            productsRepository.getProductsThrowError = false
            viewModel.onRetryClick()

            // THEN
            val reloadingEmission = awaitItem()
            assertThat(reloadingEmission).isEqualTo(CheckoutUiState.Loading)

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CheckoutUiState.Success::class)
        }
    }

}