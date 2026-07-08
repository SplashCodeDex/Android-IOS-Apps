package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing
import com.dexstudio.core.sharedui.designsystem.token.GlassContainer
import com.dexstudio.core.sharedui.designsystem.token.GlassVariant
import com.dexstudio.core.sharedui.util.pressScale

data class NavItem(val label: String, val onClick: () -> Unit)

/**
 * iOS 27 Glass Floating Navigation Bar / Tab Bar
 *
 * Uses the thick or regular glass variant depending on background complexity.
 * Implements the capsule shape (appShapes.searchBar / pill) for the floating bar.
 */
@Composable
fun GlassNavigation(
    title: String,
    modifier: Modifier = Modifier,
    logo: (@Composable () -> Unit)? = null,
    navItems: List<NavItem> = emptyList()
) {
    GlassContainer(
        variant = GlassVariant.Regular,
        shape = DeXStudioTheme.appShapes.pill,
        elevation = 16.dp, // Floating shadow
        modifier = modifier
            .fillMaxWidth()
            .height(AppSpacing.tabBarHeight) // 49dp standard
            .padding(horizontal = AppSpacing.glassBarEdgeInset) // 8dp floating inset
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (navItems.isEmpty()) Arrangement.Center else Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(horizontal = AppSpacing.xl)
        ) {
            if (logo != null) {
                logo()
            } else {
                Text(
                    text = title,
                    style = DeXStudioTheme.textStyles.headline,
                    color = DeXStudioTheme.appColors.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            if (navItems.isNotEmpty()) {
                Row(horizontalArrangement = Arrangement.spacedBy(AppSpacing.m)) {
                    navItems.forEach { item ->
                        Text(
                            text = item.label,
                            style = DeXStudioTheme.textStyles.subhead,
                            color = DeXStudioTheme.appColors.secondaryLabel,
                            modifier = Modifier.pressScale(onClick = item.onClick)
                        )
                    }
                }
            }
        }
    }
}
