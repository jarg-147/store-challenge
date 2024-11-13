package com.jargcode.storechallenge.feature.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jargcode.storechallenge.core.domain.checkout.useCase.GetCheckoutSummaryUseCase
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState
import com.jargcode.storechallenge.feature.checkout.model.toCheckoutSummaryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val getCheckoutSummaryUseCase: GetCheckoutSummaryUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CheckoutUiState> = MutableStateFlow(CheckoutUiState.Loading)
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            getCheckoutSummaryUseCase()
                .onStart {
                    _uiState.update { CheckoutUiState.Loading }
                }
                .map { summary ->
                    summary.toCheckoutSummaryUi()
                }
                .catch {
                    _uiState.update { CheckoutUiState.Error }
                }.collect { summary ->
                    _uiState.update {
                        CheckoutUiState.Success(
                            products = summary.products,
                            subtotal = summary.subtotal,
                            appliedDiscounts = summary.appliedDiscounts,
                            total = summary.total
                        )
                    }
                }
        }
    }

    fun onRetryClick() {
        init()
    }

}