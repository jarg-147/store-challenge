package com.jargcode.storechallenge.core.designsystem.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

// region Toolbar

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = StoreTheme.backgroundColors.backgroundWhite,
) {
    ToolbarWidget(
        modifier = modifier,
        text = text,
        backgroundColor = backgroundColor,
    )
}

@Composable
private fun ToolbarWidget(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = StoreTheme.backgroundColors.backgroundWhite,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = StoreTheme.titleTexts.title,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

// endregion Toolbar

// region Toolbar previews

@WidgetPreview
@Composable
private fun ToolbarPreview() {
    PreviewContainer {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            text = "Toolbar text"
        )
    }
}

// endregion Toolbar previews