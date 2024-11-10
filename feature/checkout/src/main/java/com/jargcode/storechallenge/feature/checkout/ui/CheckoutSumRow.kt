package com.jargcode.storechallenge.feature.checkout.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.feature.checkout.R

@Composable
fun CheckoutSumRow(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = StoreTheme.bodyTexts.body,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = value,
                style = StoreTheme.bodyTexts.body,
                fontWeight = FontWeight.Bold
            )
        }

        HorizontalDivider()
    }
}

@WidgetPreview
@Composable
private fun CheckoutSumRowPreview() {
    PreviewContainer {
        CheckoutSumRow(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.checkout_summary_subtotal_section),
            value = "5.00€"
        )
    }
}