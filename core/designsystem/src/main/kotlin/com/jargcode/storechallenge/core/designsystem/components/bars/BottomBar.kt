package com.jargcode.storechallenge.core.designsystem.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jargcode.storechallenge.core.designsystem.preview.PreviewContainer
import com.jargcode.storechallenge.core.designsystem.preview.WidgetPreview
import com.jargcode.storechallenge.core.designsystem.theme.StoreTheme
import com.jargcode.storechallenge.core.domain.common.navigation.Route

// region Models

data class BottomBarDestination(
    val route: Route,
    val icon: ImageVector,
    val label: String,
    val badge: String? = null,
)

// endregion Models

// region Store BottomBar

@Composable
fun StoreBottomBar(
    currentRoute: String,
    destinations: List<BottomBarDestination>,
    onItemClick: (Route) -> Unit,
) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .background(color = StoreTheme.backgroundColors.backgroundWhite)
                .fillMaxWidth()
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            destinations.forEach { destination ->
                val isSelected = currentRoute.equals(destination.route::class.simpleName, ignoreCase = true)

                BottomBarItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .weight(1f)
                        .clickable(
                            onClick = {
                                onItemClick(destination.route)
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(color = StoreTheme.rippleColors.ripple),
                        ),
                    isSelected = isSelected,
                    icon = destination.icon,
                    label = destination.label,
                    badge = destination.badge
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier,
    isSelected: Boolean,
    icon: ImageVector,
    contentDescription: String = icon.name,
    label: String,
    badge: String?,
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val tint = if (isSelected) {
            StoreTheme.iconColors.moradul
        } else {
            StoreTheme.iconColors.disabled
        }
        BadgedBox(
            badge = {
                if (!badge.isNullOrBlank()) {
                    Badge(
                        containerColor = StoreTheme.backgroundColors.backgroundMoradul,
                        contentColor = StoreTheme.iconColors.white
                    ) {
                        Text(
                            text = badge,
                            style = StoreTheme.captionTexts.captionSmall
                        )
                    }
                }
            },
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint
            )
        }

        Text(
            text = label,
            style = StoreTheme.captionTexts.captionSmall,
            color = tint
        )
    }
}

// endregion Store BottomBar

// region Store BottomBar previews

private object TestRoute1 : Route
private object TestRoute2 : Route

@WidgetPreview
@Composable
private fun StoreBottomBarPreview() {
    PreviewContainer(
        modifier = Modifier.padding(16.dp)
    ) {
        StoreBottomBar(
            currentRoute = "TestRoute1",
            destinations = listOf(
                BottomBarDestination(
                    route = TestRoute1,
                    icon = Icons.Rounded.ShoppingBag,
                    label = "Products"
                ),
                BottomBarDestination(
                    route = TestRoute2,
                    icon = Icons.Rounded.ShoppingCart,
                    label = "Cart"
                ),
            ),
            onItemClick = {}
        )
    }
}


// endregion Store BottomBar previews
