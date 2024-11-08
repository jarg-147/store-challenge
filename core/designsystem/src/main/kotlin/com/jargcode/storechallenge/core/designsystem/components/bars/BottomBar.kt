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
                    icon = destination.icon,
                    isSelected = isSelected
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier,
    icon: ImageVector,
    contentDescription: String = icon.name,
    isSelected: Boolean,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            val iconTint = if (isSelected) {
                StoreTheme.iconColors.moradul
            } else {
                StoreTheme.iconColors.disabled
            }

            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
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
                    icon = Icons.Rounded.ShoppingBag
                ),
                BottomBarDestination(
                    route = TestRoute2,
                    icon = Icons.Rounded.ShoppingCart
                ),
            ),
            onItemClick = {}
        )
    }
}


// endregion Store BottomBar previews
