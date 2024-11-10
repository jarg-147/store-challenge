package com.jargcode.storechallenge.feature.products_list

import app.cash.turbine.test
import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.core.testing.cart.FakeCartRepository
import com.jargcode.storechallenge.core.testing.coroutines.MainCoroutineRule
import com.jargcode.storechallenge.core.testing.discounts.FakeDiscountsRepository
import com.jargcode.storechallenge.core.testing.products.FakeProductsRepository
import com.jargcode.storechallenge.core.testing.products.getFakeProducts
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiState
import com.jargcode.storechallenge.feature.products_list.model.toProductUi
import kotlinx.coroutines.test.runTest
import org.junit.*

class ProductsListViewModelTest {

    private lateinit var productsRepository: FakeProductsRepository
    private lateinit var discountsRepository: FakeDiscountsRepository
    private lateinit var getProductsListUseCase: GetProductsListUseCase
    private lateinit var cartRepository: FakeCartRepository
    private lateinit var viewModel: ProductsListViewModel

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
        viewModel = ProductsListViewModel(
            getProductsListUseCase = getProductsListUseCase,
            cartRepository = cartRepository
        )
    }

    @Test
    fun `given user is in products list, when viewmodel init is called, then products are loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(ProductsListUiState.Loading)

            // WHEN
            viewModel.init()

            // THEN
            val successEmission = awaitItem()
            val mappedFakeProducts = fakeProducts.map { it.toProductUi() }
            assertThat(successEmission).isEqualTo(ProductsListUiState.Success(mappedFakeProducts))

        }
    }

    @Test
    fun `given user is in products list, when viewmodel init returns error, then error is displayed`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(ProductsListUiState.Loading)

            // WHEN
            productsRepository.shouldReturnError = true
            viewModel.init()

            // THEN
            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(ProductsListUiState.Error)

        }
    }

    @Test
    fun `given state is error, when user clicks retry, products are loaded`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(ProductsListUiState.Loading)

            productsRepository.shouldReturnError = true
            viewModel.init()

            val errorEmission = awaitItem()
            assertThat(errorEmission).isEqualTo(ProductsListUiState.Error)

            // WHEN
            productsRepository.shouldReturnError = false
            viewModel.onRetryClick()

            // THEN
            val reloadingEmission = awaitItem()
            assertThat(reloadingEmission).isEqualTo(ProductsListUiState.Loading)

            val successEmission = awaitItem()
            val mappedFakeProducts = fakeProducts.map { it.toProductUi() }
            assertThat(successEmission).isEqualTo(ProductsListUiState.Success(mappedFakeProducts))
        }
    }

    @Test
    fun `given product list is loaded, when user clicks add to cart in product, then product is added to cart`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(ProductsListUiState.Loading)

            viewModel.init()

            val successEmission = awaitItem()
            val mappedFakeProducts = fakeProducts.map { it.toProductUi() }
            assertThat(successEmission).isEqualTo(ProductsListUiState.Success(mappedFakeProducts))

            cartRepository.getUserCartItems().test {
                // WHEN
                val selectedProduct = mappedFakeProducts.first()
                viewModel.onAddProductToCartClick(selectedProduct.code)

                // THEN
                awaitItem() // Ignore first emission
                val cartItems = awaitItem()
                assertThat(cartItems.first().productCode).isEqualTo(selectedProduct.code, ignoreCase = true)
                assertThat(cartItems.first().quantity).isEqualTo(1)
            }
        }
    }

    @Test
    fun `given product list is loaded, when add product to cart fails, then error is catch`() = runTest {
        viewModel.uiState.test {
            // GIVEN
            val fakeProducts = getFakeProducts()
            productsRepository.setProducts(fakeProducts)

            val loadingEmission = awaitItem()
            assertThat(loadingEmission).isEqualTo(ProductsListUiState.Loading)

            viewModel.init()

            val successEmission = awaitItem()
            val mappedFakeProducts = fakeProducts.map { it.toProductUi() }
            assertThat(successEmission).isEqualTo(ProductsListUiState.Success(mappedFakeProducts))

            cartRepository.getUserCartItems().test {
                // WHEN
                cartRepository.addProductReturnsError = true
                val selectedProduct = mappedFakeProducts.first()
                viewModel.onAddProductToCartClick(selectedProduct.code)

                // THEN
                awaitItem() // Ignore first emission
                assertFailure { awaitItem() }
            }
        }
    }

}