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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.image.Image
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.feature.cart.model.CartUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi.DiscountInfo

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    item: CartItemUi,
    onDeleteItemClick: () -> Unit,
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
                    imageUrl = item.imageUrl,
                    contentScale = ContentScale.Fit
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = item.name,
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "x ${item.quantity}",
                        style = StoreTheme.bodyTexts.body
                    )

                    val discountInfo = item.discountInfo?.text?.value.orEmpty()
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
                    val isDiscountApplicable = item.discountInfo?.minQuantityReached ?: false
                    Text(
                        text = if (isDiscountApplicable) {
                            "*${item.price} / u"
                        } else {
                            "${item.price} / u"
                        },
                        style = StoreTheme.bodyTexts.body,
                        fontWeight = FontWeight.Medium
                    )

                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = StoreTheme.backgroundColors.backgroundMoradul,
                            contentColor = StoreTheme.iconColors.white
                        ),
                        onClick = onDeleteItemClick
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
private fun CartItemCardPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        CartItemCard(
            modifier = Modifier.fillMaxWidth(),
            item = CartUi.mock.items.first().copy(
                quantity = 2,
                discountInfo = DiscountInfo(
                    minQuantityReached = true,
                    text = StringWrapper(resId = R.string.discount_applied)
                )
            ),
            onDeleteItemClick = {}
        )

        CartItemCard(
            modifier = Modifier.fillMaxWidth(),
            item = CartUi.mock.items.first(),
            onDeleteItemClick = {}
        )

        CartItemCard(
            modifier = Modifier.fillMaxWidth(),
            item = CartUi.mock.items.first().copy(discountInfo = null),
            onDeleteItemClick = {}
        )
    }
}