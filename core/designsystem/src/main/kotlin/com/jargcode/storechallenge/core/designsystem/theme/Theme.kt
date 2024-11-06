package com.jargcode.storechallenge.core.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

// See: https://developer.android.com/develop/ui/compose/designsystems/custom#implementing-fully-custom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTheme(
    content: @Composable () -> Unit,
) {
    val rippleConfiguration = RippleConfiguration(
        color = storeRippleColors.ripple,
        rippleAlpha = RippleAlpha(0f, 0f, 0f, 0.5f)
    )

    CompositionLocalProvider(
        // Colors
        LocalStoreTextColors provides storeTextColors,
        LocalStoreBackgroundColors provides storeBackgroundColors,
        LocalStoreRippleColors provides storeRippleColors,
        LocalStoreIconColors provides storeIconColors,
        LocalRippleConfiguration provides rippleConfiguration,
        // Texts
        LocalStoreCaptionTexts provides storeCaptionTexts,
        LocalStoreBodyTexts provides storeBodyTexts,
        LocalStoreTitleTexts provides storeTitleTexts,
        LocalStoreHeadlineTexts provides storeHeadlineTexts,
        // Content
        content = content
    )
}

object StoreTheme {

    // region Colors

    val textColors: StoreTextColors
        @Composable
        get() = LocalStoreTextColors.current

    val backgroundColors: StoreBackgroundColors
        @Composable
        get() = LocalStoreBackgroundColors.current

    val rippleColors: StoreRippleColors
        @Composable
        get() = LocalStoreRippleColors.current

    val iconColors: StoreIconColors
        @Composable
        get() = LocalStoreIconColors.current

    // endregion Colors

    // region Typography

    val captionTexts: StoreCaptionTexts
        @Composable
        get() = LocalStoreCaptionTexts.current

    val bodyTexts: StoreBodyTexts
        @Composable
        get() = LocalStoreBodyTexts.current

    val titleTexts: StoreTitleTexts
        @Composable
        get() = LocalStoreTitleTexts.current

    val headlineTexts: StoreHeadlineTexts
        @Composable
        get() = LocalStoreHeadlineTexts.current

    // endregion Typography

}