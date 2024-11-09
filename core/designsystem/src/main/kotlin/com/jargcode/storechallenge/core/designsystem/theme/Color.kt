package com.jargcode.storechallenge.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// region Global colors

val grey_10 = Color(0xFFEEEEEE)
val grey_30 = Color(0xFFDDDDDD)
val grey_50 = Color(0xFFAAAAAA)
val grey_70 = Color(0xFF464646)

val white = Color(0xFFFFFFFF)

val moradul = Color(0xFF7145D6)
val ripple = Color(0xFF6739D2)

// endregion Global colors

// region Text colors

@Immutable
data class StoreTextColors(
    val text: Color,
    val textDisabled: Color,
    val textWhite: Color,
)

val LocalStoreTextColors = staticCompositionLocalOf {
    StoreTextColors(
        text = Color.Unspecified,
        textDisabled = Color.Unspecified,
        textWhite = Color.Unspecified,
    )
}

val storeTextColors = StoreTextColors(
    text = grey_70,
    textDisabled = grey_50,
    textWhite = white,
)

// endregion Text colors

// region Background colors

@Immutable
data class StoreBackgroundColors(
    val backgroundMoradul: Color,
    val backgroundDisabled: Color,
    val backgroundWhite: Color,
    val backgroundGrey: Color,
)

val LocalStoreBackgroundColors = staticCompositionLocalOf {
    StoreBackgroundColors(
        backgroundMoradul = Color.Unspecified,
        backgroundDisabled = Color.Unspecified,
        backgroundWhite = Color.Unspecified,
        backgroundGrey = Color.Unspecified,
    )
}

val storeBackgroundColors = StoreBackgroundColors(
    backgroundMoradul = moradul,
    backgroundDisabled = grey_30,
    backgroundWhite = white,
    backgroundGrey = grey_10,
)

// endregion Background colors

// region Ripple colors

@Immutable
data class StoreRippleColors(
    val ripple: Color,
)

val LocalStoreRippleColors = staticCompositionLocalOf {
    StoreRippleColors(
        ripple = Color.Unspecified,
    )
}

val storeRippleColors = StoreRippleColors(
    ripple = ripple,
)

// endregion Ripple colors

// region Icon colors

@Immutable
data class StoreIconColors(
    val moradul: Color,
    val white: Color,
    val disabled: Color,
)

val LocalStoreIconColors = staticCompositionLocalOf {
    StoreIconColors(
        moradul = Color.Unspecified,
        white = Color.Unspecified,
        disabled = Color.Unspecified,
    )
}

val storeIconColors = StoreIconColors(
    moradul = moradul,
    white = white,
    disabled = grey_50,
)

// endregion Icon colors