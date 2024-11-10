package com.jargcode.storechallenge.feature.checkout.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.button.PrimaryButton
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.feature.checkout.R
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.AppliedDiscountUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.CheckoutItemUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiEvent
import com.jargcode.storechallenge.feature.checkout.model.CheckoutUiEvent.OnPayClick

@Composable
fun CheckoutContent(
    modifier: Modifier = Modifier,
    items: List<CheckoutItemUi>,
    subtotal: String,
    appliedDiscounts: List<AppliedDiscountUi>,
    total: String,
    onUiEvent: (CheckoutUiEvent) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
        ) {

            // region Header

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.checkout_summary_header),
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider()
                }
            }

            // region Header

            // region Products

            item {
                Text(
                    text = stringResource(R.string.checkout_summary_products_section),
                    style = StoreTheme.bodyTexts.body,
                    fontWeight = FontWeight.Medium
                )
            }

            items(
                items = items,
                key = { item -> item.code }
            ) { item ->
                CheckoutItemRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    name = item.name,
                    value = item.totalPrice,
                    description = "x ${item.quantity} - ${item.pricePerUnit} / u"
                )
            }

            // endregion Products

            if (appliedDiscounts.isNotEmpty()) {

                // region Subtotal

                item {
                    CheckoutSumRow(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.checkout_summary_subtotal_section),
                        value = subtotal
                    )
                }

                // region Subtotal

                // region Discounts

                item {
                    Text(
                        text = stringResource(R.string.checkout_summary_discount_section),
                        style = StoreTheme.bodyTexts.body,
                        fontWeight = FontWeight.Medium
                    )
                }

                items(
                    items = appliedDiscounts,
                ) { discount ->
                    CheckoutItemRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        name = discount.discountInfo.value,
                        value = "-${discount.totalDiscount}",
                        description = "x ${discount.timesApplied}"
                    )
                }

                // endregion Discounts

            }

            // region Total

            item {
                CheckoutSumRow(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.checkout_summary_total_section),
                    value = total
                )
            }

            // endregion Total
        }

        Column(
            modifier = Modifier
                .background(StoreTheme.backgroundColors.backgroundWhite)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HorizontalDivider()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                text = stringResource(R.string.checkout_summary_pay_button),
                onClick = {
                    onUiEvent(OnPayClick)
                }
            )
        }
    }
}

@WidgetPreview
@Composable
private fun CheckoutContentWithDiscountsPreview() {
    PreviewContainer {
        CheckoutContent(
            modifier = Modifier.fillMaxSize(),
            items = CheckoutSummaryUi.mock.items,
            subtotal = CheckoutSummaryUi.mock.subtotal,
            appliedDiscounts = CheckoutSummaryUi.mock.appliedDiscounts,
            total = CheckoutSummaryUi.mock.total,
            onUiEvent = {}
        )
    }
}

@WidgetPreview
@Composable
private fun CheckoutContentWithoutDiscountsPreview() {
    PreviewContainer {
        CheckoutContent(
            modifier = Modifier.fillMaxSize(),
            items = CheckoutSummaryUi.mock.items,
            subtotal = CheckoutSummaryUi.mock.subtotal,
            appliedDiscounts = emptyList(),
            total = "10.00€",
            onUiEvent = {}
        )
    }
}