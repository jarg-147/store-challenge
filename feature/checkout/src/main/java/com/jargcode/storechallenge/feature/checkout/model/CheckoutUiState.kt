package com.jargcode.storechallenge.feature.checkout.model

import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.AppliedDiscountUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.CheckoutItemUi

sealed interface CheckoutUiState {

    data object Loading : CheckoutUiState

    data object Error : CheckoutUiState

    data class Success(
        val items: List<CheckoutItemUi>,
        val subtotal: String,
        val appliedDiscounts: List<AppliedDiscountUi>,
        val total: String,
    ) : CheckoutUiState

}