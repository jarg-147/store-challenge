package com.jargcode.storechallenge.feature.products_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import com.jargcode.storechallenge.feature.products_list.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProductsListUseCase: GetProductsListUseCase,
    private val cartRepository: CartRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductsListUiState> = MutableStateFlow(ProductsListUiState.Loading)
    val uiState: StateFlow<ProductsListUiState> = _uiState.asStateFlow()

    private val _vmEvent = Channel<ProductsListVMEvent>()
    val vmEvent: Flow<ProductsListVMEvent> = _vmEvent.receiveAsFlow()

    fun init() {
        viewModelScope.launch {
            getProductsListUseCase()
                .onStart {
                    _uiState.update { ProductsListUiState.Loading }
                }
                .map { products ->
                    products.map { product ->
                        product.toProductUi()
                    }
                }.catch { ex ->
                    _uiState.update { ProductsListUiState.Error }
                }.collect { products ->
                    _uiState.update { ProductsListUiState.Success(products) }
                }
        }
    }

    fun onAddProductToCartClick(productId: String) {
        viewModelScope.launch {
            try {
                cartRepository.addProduct(productId = productId)
                _vmEvent.send(ProductsListVMEvent.ShowProductAddedToCartSuccess)
            } catch (ex: Exception) {
                if (ex is CancellationException) throw ex
                _vmEvent.send(ProductsListVMEvent.ShowProductAddedToCartError)
            }
        }
    }

    fun onRetryClick() {
        init()
    }

}