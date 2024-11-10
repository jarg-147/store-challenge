package com.jargcode.storechallenge.feature.cart

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.*
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.cart.FakeCartRepository
import com.jargcode.storechallenge.core.testing.coroutines.MainCoroutineRule
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.getFakeProducts
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.model.CartUiState
import com.jargcode.storechallenge.feature.cart.model.CartVMEvent
import kotlinx.coroutines.test.runTest
import org.junit.*

class CartViewModelTest {

    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository
    private lateinit var cartRepository: FakeCartRepository

    private lateinit var getProductsListUseCase: GetProductsListUseCase
    private lateinit var getUserCartUseCase: GetUserCartUseCase

    private lateinit var viewModel: CartViewModel

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

        viewModel = CartViewModel(
            getUserCartUseCase = getUserCartUseCase,
            cartRepository = cartRepository
        )
    }

    @Test
    fun `given user is in cart, when viewmodel init is called, then cart items are loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val cartProduct = fakeProducts.first()
            cartRepository.addProduct(cartProduct.code)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            // WHEN
            viewModel.init()

            // THEN
            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CartUiState.Success::class)

            successEmission as CartUiState.Success
            assertThat(successEmission.items.size).isEqualTo(1)
            assertThat(successEmission.items.first().code).isEqualTo(cartProduct.code)
            assertThat(successEmission.totalPrice).isEqualTo(cartProduct.price.toFormattedPrice())
        }
    }

    @Test
    fun `given user is in cart, when viewmodel init returns error, then error is displayed`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            // WHEN
            productsRepository.shouldReturnError = true
            viewModel.init()

            // THEN
            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(CartUiState.Error)

        }
    }

    @Test
    fun `given state is error, when user clicks retry, products are loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val cartProduct = fakeProducts.first()
            cartRepository.addProduct(cartProduct.code)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            productsRepository.shouldReturnError = true
            viewModel.init()

            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(CartUiState.Error)

            // WHEN
            productsRepository.shouldReturnError = false
            viewModel.onRetryClick()

            // THEN
            val reloadingEmission = awaitItem()
            assertThat(reloadingEmission).isEqualTo(CartUiState.Loading)

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CartUiState.Success::class)

            successEmission as CartUiState.Success
            assertThat(successEmission.items.size).isEqualTo(1)
            assertThat(successEmission.items.first().code).isEqualTo(cartProduct.code)
            assertThat(successEmission.totalPrice).isEqualTo(cartProduct.price.toFormattedPrice())
        }
    }

    @Test
    fun `given cart is loaded, when user clicks remove product, then product is removed`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val cartProduct = fakeProducts.first()
            cartRepository.addProduct(cartProduct.code)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            viewModel.init()

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CartUiState.Success::class)

            successEmission as CartUiState.Success
            assertThat(successEmission.items.size).isEqualTo(1)
            assertThat(successEmission.items.first().code).isEqualTo(cartProduct.code)
            assertThat(successEmission.totalPrice).isEqualTo(cartProduct.price.toFormattedPrice())

            cartRepository.getUserCartItems().test {
                // WHEN
                viewModel.onDeleteItemClick(cartProduct.code)

                // THEN
                awaitItem() // Ignore first emission
                val cartItems = awaitItem()
                assertThat(cartItems).isEmpty()
            }

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `given cart is loaded, when delete product form cart fails, then error is catch`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val cartProduct = fakeProducts.first()
            cartRepository.addProduct(cartProduct.code)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            viewModel.init()

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CartUiState.Success::class)

            successEmission as CartUiState.Success
            assertThat(successEmission.items.size).isEqualTo(1)
            assertThat(successEmission.items.first().code).isEqualTo(cartProduct.code)
            assertThat(successEmission.totalPrice).isEqualTo(cartProduct.price.toFormattedPrice())

            viewModel.vmEvent.test {
                // WHEN
                cartRepository.removeProductReturnsError = true
                viewModel.onDeleteItemClick(cartProduct.code)

                // THEN
                val errorEmission = awaitItem()
                assertThat(errorEmission).isEqualTo(CartVMEvent.ShowProductDeleteError)
            }
        }
    }

    @Test
    fun `given cart is loaded, when saved product is unknown, then empty cart is returned`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            cartRepository.addProduct("123")

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(CartUiState.Loading)

            viewModel.init()

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(CartUiState.Success::class)

            successEmission as CartUiState.Success
            assertThat(successEmission.items).isEmpty()
        }
    }

}