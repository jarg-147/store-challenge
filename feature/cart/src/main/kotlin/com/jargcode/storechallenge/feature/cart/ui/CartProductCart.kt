package com.jargcode.storechallenge.feature.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.image.Image
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.feature.cart.model.CartUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi.DiscountInfo

@Composable
fun CartProductCart(
    modifier: Modifier = Modifier,
    product: CartProductUi,
    onDeleteProductClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = StoreTheme.backgroundColors.backgroundWhite
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    imageUrl = product.imageUrl,
                    contentScale = ContentScale.Fit
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = product.name,
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "x ${product.quantity}",
                        style = StoreTheme.bodyTexts.body
                    )

                    val discountInfo = product.discountInfo?.text?.value.orEmpty()
                    if (discountInfo.isNotBlank()) {
                        Text(
                            text = discountInfo,
                            style = StoreTheme.captionTexts.captionSmallMoradul,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    val isDiscountApplicable = product.discountInfo?.isApplicable ?: false
                    val productPrice = if (isDiscountApplicable) {
                        stringResource(R.string.product_price_per_unit_with_discount, product.price)
                    } else {
                        stringResource(R.string.product_price_per_unit_without_discount, product.price)
                    }

                    Text(
                        text = productPrice,
                        style = StoreTheme.bodyTexts.body,
                        fontWeight = FontWeight.Medium
                    )

                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = StoreTheme.backgroundColors.backgroundMoradul,
                            contentColor = StoreTheme.iconColors.white
                        ),
                        onClick = onDeleteProductClick
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.DeleteOutline,
                            contentDescription = Icons.Rounded.DeleteOutline.name
                        )
                    }
                }
            }
        }
    }
}

@WidgetPreview
@Composable
private fun CartProductCardPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        CartProductCart(
            modifier = Modifier.fillMaxWidth(),
            product = CartUi.mock.cartProducts.first().copy(
                quantity = 2,
                discountInfo = DiscountInfo(
                    isApplicable = true,
                    text = StringWrapper(resId = R.string.discount_applied)
                )
            ),
            onDeleteProductClick = {}
        )

        CartProductCart(
            modifier = Modifier.fillMaxWidth(),
            product = CartUi.mock.cartProducts.first(),
            onDeleteProductClick = {}
        )

        CartProductCart(
            modifier = Modifier.fillMaxWidth(),
            product = CartUi.mock.cartProducts.first().copy(discountInfo = null),
            onDeleteProductClick = {}
        )
    }
}