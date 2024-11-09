package com.jargcode.storechallenge.feature.products_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.button.PrimaryButton
import com.jargcode.storechallenge.core.designsystem.components.image.Image
import com.jargcode.storechallenge.core.designsystem.components.text.DiscountTag
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.feature.products_list.R
import com.jargcode.storechallenge.feature.products_list.model.ProductUi

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductUi,
    onAddToCartClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = StoreTheme.backgroundColors.backgroundWhite
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 6.dp
        )
    ) {
        val discountText = product.discountText?.value.orEmpty()

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    imageUrl = product.imageUrl,
                    contentScale = ContentScale.Fit
                )
                if (discountText.isNotBlank()) {
                    DiscountTag(
                        modifier = Modifier.padding(
                            top = 8.dp,
                            start = 8.dp
                        )
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = product.name,
                        style = StoreTheme.titleTexts.title,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (discountText.isNotBlank()) {
                        Text(
                            text = discountText,
                            style = StoreTheme.captionTexts.captionSmallMoradul,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Text(
                    text = product.price,
                    style = StoreTheme.bodyTexts.body,
                    fontWeight = FontWeight.Medium
                )
            }

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                text = stringResource(R.string.add_to_cart),
                onClick = onAddToCartClick
            )
        }
    }
}

@WidgetPreview
@Composable
private fun ProductCardPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        ProductCard(
            modifier = Modifier.fillMaxWidth(),
            product = ProductUi.mock,
            onAddToCartClick = {}
        )
    }
}