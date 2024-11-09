package com.jargcode.storechallenge.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.feature.cart.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getUserCartUseCase: GetUserCartUseCase,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CartUiState> = MutableStateFlow(CartUiState.Loading)
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    private val _vmEvent = Channel<CartVMEvent>()
    val vmEvent: Flow<CartVMEvent> = _vmEvent.receiveAsFlow()

    fun init() {
        viewModelScope.launch {
            getUserCartUseCase()
                .map { cart ->
                    cart.toCartUi()
                }
                .catch {
                    _uiState.update { CartUiState.Error }
                }.collect { cart ->
                    _uiState.update {
                        CartUiState.Success(
                            items = cart.items,
                            totalPrice = cart.total,
                        )
                    }
                }
        }

    }

    fun onRetryClick() {
        init()
    }

    fun onDeleteItemClick(productCode: String) {
        viewModelScope.launch {
            try {
                cartRepository.removeCartProduct(productCode = productCode)
                _vmEvent.send(CartVMEvent.ShowProductDeleteSuccess)
            } catch (ex: Exception) {
                if (ex is CancellationException) throw ex
                _vmEvent.send(CartVMEvent.ShowProductDeleteError)
            }
        }
    }

}