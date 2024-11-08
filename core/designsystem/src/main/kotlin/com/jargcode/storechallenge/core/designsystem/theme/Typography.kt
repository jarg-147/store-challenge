package com.jargcode.storechallenge.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer

// region Common

val baseText: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

// endregion Common

// region Caption

private val caption: TextStyle
    @Composable
    get() = baseText

private val captionWhite: TextStyle
    @Composable
    get() = baseText.copy(
        color = white
    )

private val captionSmall: TextStyle
    @Composable
    get() = caption.copy(
        fontSize = 12.sp
    )

private val captionSmallMoradul: TextStyle
    @Composable
    get() = caption.copy(
        fontSize = 12.sp,
        color = moradul
    )

@Immutable
data class StoreCaptionTexts(
    val caption: TextStyle,
    val captionWhite: TextStyle,
    val captionSmall: TextStyle,
    val captionSmallMoradul: TextStyle,
)

val LocalStoreCaptionTexts = staticCompositionLocalOf {
    StoreCaptionTexts(
        caption = TextStyle.Default,
        captionWhite = TextStyle.Default,
        captionSmall = TextStyle.Default,
        captionSmallMoradul = TextStyle.Default
    )
}

val storeCaptionTexts: StoreCaptionTexts
    @Composable
    get() = StoreCaptionTexts(
        caption = caption,
        captionWhite = captionWhite,
        captionSmall = captionSmall,
        captionSmallMoradul = captionSmallMoradul
    )

// endregion Caption

// region Body

private val body: TextStyle
    @Composable
    get() = baseText.copy(
        fontSize = 16.sp
    )

@Immutable
data class StoreBodyTexts(
    val body: TextStyle,
)

val LocalStoreBodyTexts = staticCompositionLocalOf {
    StoreBodyTexts(
        body = TextStyle.Default
    )
}

val storeBodyTexts: StoreBodyTexts
    @Composable
    get() = StoreBodyTexts(
        body = body,
    )

// endregion Body

// region Title

private val title: TextStyle
    @Composable
    get() = baseText.copy(
        fontSize = 18.sp
    )

private val titleWhite: TextStyle
    @Composable
    get() = baseText.copy(
        fontSize = 18.sp,
        color = white
    )

@Immutable
data class StoreTitleTexts(
    val title: TextStyle,
    val titleWhite: TextStyle,
)

val LocalStoreTitleTexts = staticCompositionLocalOf {
    StoreTitleTexts(
        title = TextStyle.Default,
        titleWhite = TextStyle.Default,
    )
}

val storeTitleTexts: StoreTitleTexts
    @Composable
    get() = StoreTitleTexts(
        title = title,
        titleWhite = titleWhite,
    )

// endregion Title

// region Headline

private val headline: TextStyle
    @Composable
    get() = baseText.copy(
        fontSize = 20.sp
    )

@Immutable
data class StoreHeadlineTexts(
    val headline: TextStyle,
)

val LocalStoreHeadlineTexts = staticCompositionLocalOf {
    StoreHeadlineTexts(
        headline = TextStyle.Default
    )
}

val storeHeadlineTexts: StoreHeadlineTexts
    @Composable
    get() = StoreHeadlineTexts(
        headline = headline
    )

// endregion Headline

// region Style previews

@Preview
@Composable
private fun TextStylesPreview() {
    PreviewContainer {
        val captionStyles = listOf(
            "caption" to StoreTheme.captionTexts.caption,
            "captionSmall" to StoreTheme.captionTexts.captionSmall,
            "captionSmallMoradul" to StoreTheme.captionTexts.captionSmallMoradul,
            "captionWhite" to StoreTheme.captionTexts.captionWhite
        )

        val bodyStyles = listOf(
            "body" to StoreTheme.bodyTexts.body
        )

        val titleStyles = listOf(
            "title" to StoreTheme.titleTexts.title,
            "titleWhite" to StoreTheme.titleTexts.titleWhite
        )

        val headlineStyles = listOf(
            "headline" to StoreTheme.headlineTexts.headline
        )

        val allStyles = captionStyles + bodyStyles + titleStyles + headlineStyles

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(0.8f)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = allStyles
            ) { (label, textStyle) ->
                Text(
                    text = label,
                    style = textStyle
                )
            }
        }
    }
}

// endregion Style previews