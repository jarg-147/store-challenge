package com.jargcode.storechallenge.feature.checkout.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi

@Composable
fun CheckoutItemRow(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    description: String = ""
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = StoreTheme.captionTexts.caption,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = value,
                style = StoreTheme.captionTexts.caption,
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.alpha(0.6f),
                text = description,
                style = StoreTheme.captionTexts.captionSmall
            )
        }
    }
}

@WidgetPreview
@Composable
private fun CheckoutItemRowPreview() {
    PreviewContainer {
        CheckoutItemRow(
            modifier = Modifier.fillMaxWidth(),
            name = CheckoutSummaryUi.mock.products.first().name,
            value = CheckoutSummaryUi.mock.products.first().totalPrice,
            description = CheckoutSummaryUi.mock.products.first().pricePerUnit,

            )
    }
}