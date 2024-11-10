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
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent
import com.jargcode.storechallenge.feature.cart.model.CartUiEvent.OnGoToCheckoutClick

@Composable
fun CartContent(
    modifier: Modifier,
    cartProducts: List<CartProductUi>,
    cartTotal: String,
    anyApplicableDiscount: Boolean,
    onUiEvent: (CartUiEvent) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
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
                        text = stringResource(R.string.products_in_cart_header),
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(
                items = cartProducts,
                key = { item -> item.code }
            ) { item ->
                CartProductCart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    product = item,
                    onDeleteProductClick = {
                        onUiEvent(CartUiEvent.OnDeleteProductFromCartClick(productCode = item.code))
                    }
                )
            }

            if (anyApplicableDiscount) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.checkout_discounts_disclaimer),
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
                    text = stringResource(R.string.total_price_label),
                    style = StoreTheme.titleTexts.title,
                    fontWeight = FontWeight.SemiBold
                )

                val totalContentDescription = stringResource(R.string.total_price_content_description)
                val totalText = if (anyApplicableDiscount) {
                    stringResource(R.string.total_price_value_with_discounts, cartTotal)
                } else {
                    cartTotal
                }

                Text(
                    modifier = Modifier.semantics {
                        contentDescription = totalContentDescription
                    },
                    text = totalText,
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
                text = stringResource(R.string.checkout_button_text),
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
            cartProducts = CartUi.mock.cartProducts,
            cartTotal = CartUi.mock.totalPriceWithoutDiscounts,
            anyApplicableDiscount = true,
            onUiEvent = {}
        )
    }
}