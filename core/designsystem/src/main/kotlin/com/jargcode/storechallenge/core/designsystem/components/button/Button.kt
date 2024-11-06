package com.jargcode.storechallenge.core.designsystem.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme

// region Primary button

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    ButtonWidget(
        modifier = modifier,
        icon = icon,
        text = text,
        isEnabled = isEnabled,
        textColor = StoreTheme.textColors.textWhite,
        disabledTextColor = StoreTheme.textColors.textDisabled,
        backgroundColor = StoreTheme.backgroundColors.backgroundMoradul,
        disabledBackgroundColor = StoreTheme.backgroundColors.backgroundDisabled,
        rippleColor = StoreTheme.rippleColors.ripple,
        onClick = onClick,
    )
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    isEnabled: Boolean = true,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    disabledBackgroundColor: Color,
    rippleColor: Color,
    onClick: () -> Unit,
) {
    ButtonWidget(
        modifier = modifier,
        icon = icon,
        text = text,
        isEnabled = isEnabled,
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        rippleColor = rippleColor,
        onClick = onClick,
    )
}

// region Button widget

@Composable
private fun ButtonWidget(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    isEnabled: Boolean,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    disabledBackgroundColor: Color,
    rippleColor: Color,
    onClick: () -> Unit,
) {

    val buttonBackgroundColor = if (isEnabled) {
        backgroundColor
    } else {
        disabledBackgroundColor
    }

    val buttonTextColor = if (isEnabled) {
        textColor
    } else {
        disabledTextColor
    }

    Box(
        modifier = modifier
            .heightIn(min = 50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = buttonBackgroundColor)
            .clickable(
                enabled = isEnabled,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = rippleColor)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = icon,
                    contentDescription = "Icon button",
                    tint = buttonTextColor
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = text,
                style = StoreTheme.titleTexts.titleWhite,
                fontSize = 18.sp,
                color = buttonTextColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

// endregion Button widget

// region Button previews

@WidgetPreview
@Composable
private fun PrimaryButtonPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Primary button",
            onClick = {}
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Primary button",
            icon = Icons.Rounded.CreditCard,
            onClick = {}
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Primary button",
            isEnabled = false,
            onClick = {}
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Primary button",
            isEnabled = false,
            icon = Icons.Rounded.CreditCard,
            onClick = {}
        )
    }
}

@WidgetPreview
@Composable
private fun CustomButtonPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Custom button",
            textColor = Color.White,
            disabledTextColor = Color.White.copy(alpha = 0.5f),
            backgroundColor = Color.Red,
            disabledBackgroundColor = Color.Red.copy(alpha = 0.5f),
            rippleColor = StoreTheme.backgroundColors.backgroundMoradul.copy(alpha = 0.8f),
            onClick = {}
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Custom button",
            isEnabled = false,
            textColor = Color.White,
            disabledTextColor = Color.White.copy(alpha = 0.5f),
            backgroundColor = Color.Red,
            disabledBackgroundColor = Color.Red.copy(alpha = 0.5f),
            rippleColor = StoreTheme.rippleColors.ripple,
            onClick = {}
        )
    }
}

// endregion Button previews