package com.jargcode.storechallenge.core.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview

// region Image components

@Composable
fun Image(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = 1f,
    colorTint: Color? = null,
    clipToBounds: Boolean = true,
) {
    ImageWidget(
        modifier = modifier,
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        alpha = alpha,
        colorTint = colorTint,
        clipToBounds = clipToBounds
    )
}

// region Image components

// endregion Image widget

@Composable
private fun ImageWidget(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = 1f,
    colorTint: Color? = null,
    clipToBounds: Boolean = true,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {
            Box(modifier = modifier.background(Color.Gray))
        },
        error = {
            Box(modifier = modifier.background(Color.Gray))
        },
        alpha = alpha,
        colorFilter = colorTint?.let { ColorFilter.tint(it) },
        clipToBounds = clipToBounds
    )
}

// endregion Image widget

// region Image previews

@WidgetPreview
@Composable
private fun ImagePreviews() {
    PreviewContainer {
        Image(
            modifier = Modifier.size(100.dp),
            imageUrl = "https://m.media-amazon.com/images/I/61gn3DFLlWL._AC_UY1000_.jpg"
        )
    }
}

// endregion Image previews