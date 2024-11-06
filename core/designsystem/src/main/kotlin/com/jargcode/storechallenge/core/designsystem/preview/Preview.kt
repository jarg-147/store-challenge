package com.jargcode.storechallenge.core.designsystem.preview

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

@Preview(
    name = "Device",
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440",
    backgroundColor = 0xFFF8F8F8
)
annotation class DevicePreview

@Preview(
    name = "Widget",
    showBackground = true,
    backgroundColor = 0xFFF8F8F8
)
annotation class WidgetPreview

@Composable
fun PreviewContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    StoreTheme {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}