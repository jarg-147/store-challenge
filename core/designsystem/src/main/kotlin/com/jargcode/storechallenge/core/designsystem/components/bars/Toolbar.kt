package com.jargcode.storechallenge.core.designsystem.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    onBackIconClick: (() -> Unit)? = null,
) {
    ToolbarWidget(
        modifier = modifier,
        text = text,
        backgroundColor = backgroundColor,
        onBackIconClick = onBackIconClick
    )
}

@Composable
private fun ToolbarWidget(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = StoreTheme.backgroundColors.backgroundWhite,
    onBackIconClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBackIconClick != null) {
                IconButton(
                    onClick = onBackIconClick,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null,
                        tint = StoreTheme.iconColors.moradul
                    )
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth().weight(1f),
                text = text,
                style = StoreTheme.titleTexts.title,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (onBackIconClick != null) {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

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

        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            text = "Toolbar text",
            onBackIconClick = {}
        )
    }
}

// endregion Toolbar previews