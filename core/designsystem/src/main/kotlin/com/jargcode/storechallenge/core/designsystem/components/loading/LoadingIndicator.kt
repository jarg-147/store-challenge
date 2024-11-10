package com.jargcode.storechallenge.core.designsystem.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.R
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
) {
    val testTag = stringResource(R.string.loading_indicator_content_description)
    Box(
        modifier = modifier
            .background(Color.Black.copy(0.5f))
            .clickable(onClick = {})
            .semantics {
                contentDescription = testTag
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = StoreTheme.iconColors.moradul,
            strokeCap = StrokeCap.Round,
        )
    }
}

@WidgetPreview
@Composable
private fun LoadingIndicatorPreview() {
    PreviewContainer {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
    }
}