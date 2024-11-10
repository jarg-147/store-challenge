package com.jargcode.storechallenge.feature.checkout.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiState

class CheckoutPreviewParameterProvider : PreviewParameterProvider<CheckoutUiState> {

    override val values: Sequence<CheckoutUiState>
        get() = sequenceOf(
            CheckoutUiState.Loading,
            CheckoutUiState.Error,
            CheckoutUiState.Success(
                items = CheckoutSummaryUi.mock.items,
                subtotal = CheckoutSummaryUi.mock.subtotal,
                appliedDiscounts = CheckoutSummaryUi.mock.appliedDiscounts,
                total = CheckoutSummaryUi.mock.total,
            )
        )

}