package com.jargcode.storechallenge.core.designsystem.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ProductionQuantityLimits
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.components.button.PrimaryButton
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    title: String,
    description: String,
    buttonText: String = "",
    onButtonClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = icon,
            contentDescription = contentDescription,
        )

        Text(
            text = title,
            style = StoreTheme.headlineTexts.headline,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = StoreTheme.bodyTexts.body,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        if (onButtonClick != null) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = buttonText,
                onClick = onButtonClick
            )
        }
    }
}

@Preview
@Composable
private fun EmptyViewPreview() {
    PreviewContainer {
        EmptyView(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            icon = Icons.Rounded.ProductionQuantityLimits,
            title = "Title",
            description = "Text"
        )
    }
}