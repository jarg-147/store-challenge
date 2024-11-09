package com.jargcode.storechallenge.core.designsystem.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Discount
import androidx.compose.material.icons.rounded.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.R
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

// region Tag

@Composable
fun DiscountTag(
    modifier: Modifier = Modifier,
) {
    TagWidget(
        modifier = modifier,
        icon = Icons.Rounded.LocalOffer,
        contentDescription = Icons.Rounded.Discount.name,
        text = stringResource(R.string.discount)
    )
}

// endregion Tag

// region Tag widget

@Composable
private fun TagWidget(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String?,
    text: String,
) {
    Box(
        modifier = modifier.background(
            StoreTheme.backgroundColors.backgroundMoradul,
            RoundedCornerShape(8.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = StoreTheme.iconColors.white
            )

            Text(
                text = text,
                style = StoreTheme.captionTexts.captionWhite
            )
        }
    }
}

// endregion Tag widget

// region Tag previews

@WidgetPreview
@Composable
private fun TagPreviews() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        DiscountTag()
    }
}

// endregion Tag previews