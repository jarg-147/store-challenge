package com.jargcode.storechallenge.feature.cart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.button.PrimaryButton
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.feature.cart.model.CartUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent.OnGoToCheckoutClick

@Composable
fun CartContent(
    modifier: Modifier,
    items: List<CartItemUi>,
    total: String,
    onUiEvent: (CartUiEvent) -> Unit,
) {
    Column(
        modifier = modifier.testTag(stringResource(R.string.cart_content_test_tag))
    ) {
        val anyProductWithApplicableDiscount = items.any { product ->
            product.discountInfo?.minQuantityReached == true
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.products_in_your_cart),
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(
                items = items,
                key = { item -> item.code }
            ) { item ->
                CartItemCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    item = item,
                    onDeleteItemClick = {
                        onUiEvent(CartUiEvent.OnDeleteItemFromCartClick(itemCode = item.code))
                    }
                )
            }

            if (anyProductWithApplicableDiscount) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.checkout_disclaimer),
                        style = StoreTheme.captionTexts.captionSmall,
                        textAlign = TextAlign.End
                    )
                }
            }
        }

        Column(
            modifier = Modifier.background(StoreTheme.backgroundColors.backgroundWhite),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.total_price),
                    style = StoreTheme.titleTexts.title
                )

                val totalContentDescription = stringResource(R.string.total_price_content_description)
                Text(
                    modifier = Modifier.semantics {
                        contentDescription = totalContentDescription
                    },
                    text = if (anyProductWithApplicableDiscount) {
                        "*$total"
                    } else {
                        total
                    },
                    style = StoreTheme.titleTexts.title.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                text = stringResource(R.string.checkout),
                icon = Icons.Rounded.CreditCard,
                onClick = {
                    onUiEvent(OnGoToCheckoutClick)
                }
            )
        }
    }
}

@WidgetPreview
@Composable
private fun CartContentPreview() {
    PreviewContainer {
        CartContent(
            modifier = Modifier.fillMaxSize(),
            items = CartUi.mock.items,
            total = CartUi.mock.total,
            onUiEvent = {}
        )
    }
}