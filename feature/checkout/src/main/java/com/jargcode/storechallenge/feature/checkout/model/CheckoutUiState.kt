package com.jargcode.storechallenge.feature.checkout.model

import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.AppliedDiscountUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.CheckoutProductUi

sealed interface CheckoutUiState {

    data object Loading : CheckoutUiState

    data object Error : CheckoutUiState

    data class Success(
        val products: List<CheckoutProductUi>,
        val subtotal: String,
        val appliedDiscounts: List<AppliedDiscountUi>,
        val total: String,
    ) : CheckoutUiState

}