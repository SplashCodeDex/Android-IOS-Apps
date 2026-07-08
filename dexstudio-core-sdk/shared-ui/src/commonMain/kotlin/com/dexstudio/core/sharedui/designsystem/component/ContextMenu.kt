package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing
import com.dexstudio.core.sharedui.designsystem.token.GlassContainer
import com.dexstudio.core.sharedui.designsystem.token.GlassVariant
import com.dexstudio.core.sharedui.util.pressScale

data class ContextMenuItem(
    val label: String,
    val isDestructive: Boolean = false,
    val onClick: () -> Unit
)

/**
 * iOS 27 Context Menu (DESIGN.md §1.2)
 *
 * Floating glass panel that appears on long-press.
 * Features Thick glass variant and tight item spacing.
 */
@Composable
fun ContextMenu(
    items: List<ContextMenuItem>,
    modifier: Modifier = Modifier
) {
    GlassContainer(
        variant = GlassVariant.Thick,
        shape = DeXStudioTheme.appShapes.medium, // 14dp
        elevation = 30.dp, // High elevation for floating menus
        modifier = modifier.width(250.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index, item ->
                val textColor = if (item.isDestructive) {
                    DeXStudioTheme.appColors.systemRed
                } else {
                    DeXStudioTheme.appColors.label
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .pressScale(onClick = item.onClick)
                        .clickable(onClick = item.onClick) // Fallback for interaction
                        .padding(horizontal = AppSpacing.m, vertical = AppSpacing.s)
                        .heightIn(min = AppSpacing.minimumTouchTarget)
                ) {
                    Text(
                        text = item.label,
                        style = DeXStudioTheme.textStyles.body,
                        color = textColor
                    )
                    // Trailing icon could go here
                }
                
                if (index < items.size - 1) {
                    HorizontalDivider(
                        color = DeXStudioTheme.appColors.separator,
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}
