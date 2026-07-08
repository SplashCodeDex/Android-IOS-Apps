package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing

/**
 * iOS 27 Grouped List Container (DESIGN.md §2.1)
 *
 * Standard inset grouped list style. Used in Settings, Catalog, Profiles.
 */
@Composable
fun ListSection(
    title: String? = null,
    footer: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.listInsetHorizontal, vertical = AppSpacing.m)
    ) {
        if (title != null) {
            Text(
                text = title.uppercase(),
                style = DeXStudioTheme.textStyles.footnote,
                color = DeXStudioTheme.appColors.secondaryLabel,
                modifier = Modifier.padding(start = AppSpacing.l, bottom = AppSpacing.sectionHeaderBottomPadding)
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(DeXStudioTheme.appShapes.standard)
                .background(DeXStudioTheme.appColors.secondarySystemGroupedBackground)
        ) {
            content()
        }
        
        if (footer != null) {
            Text(
                text = footer,
                style = DeXStudioTheme.textStyles.caption1,
                color = DeXStudioTheme.appColors.secondaryLabel,
                modifier = Modifier.padding(start = AppSpacing.l, top = AppSpacing.s, end = AppSpacing.l)
            )
        }
    }
}

/**
 * iOS 27 List Row (DESIGN.md §2.1)
 *
 * A single row inside a [ListSection].
 */
@Composable
fun ListRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    value: String? = null,
    icon: (@Composable () -> Unit)? = null,
    showChevron: Boolean = false,
    showDivider: Boolean = true,
    onClick: (() -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else Modifier
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppSpacing.m,
                    end = AppSpacing.m,
                    top = AppSpacing.listRowVerticalPadding,
                    bottom = AppSpacing.listRowVerticalPadding
                )
                .heightIn(min = AppSpacing.minimumTouchTarget)
        ) {
            // Leading Icon
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(AppSpacing.listRowIconSize)
                        .padding(end = AppSpacing.listRowIconGap),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }
            }
            
            // Text Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = DeXStudioTheme.textStyles.body,
                    color = DeXStudioTheme.appColors.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = DeXStudioTheme.textStyles.subhead,
                        color = DeXStudioTheme.appColors.secondaryLabel,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            // Trailing Content (Value, Toggle, or Chevron)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(start = AppSpacing.s)
            ) {
                if (value != null) {
                    Text(
                        text = value,
                        style = DeXStudioTheme.textStyles.body,
                        color = DeXStudioTheme.appColors.secondaryLabel,
                        modifier = Modifier.padding(end = if (showChevron) AppSpacing.xs else 0.dp)
                    )
                }
                
                if (trailingContent != null) {
                    trailingContent()
                } else if (showChevron) {
                    Text(
                        text = ">", // Emulated chevron until we add the actual icon
                        style = DeXStudioTheme.textStyles.body,
                        color = DeXStudioTheme.appColors.tertiaryLabel
                    )
                }
            }
        }
        
        // Hairline Divider (inset matches iOS standard)
        if (showDivider) {
            val dividerInset = if (icon != null) AppSpacing.m + AppSpacing.listRowIconSize + AppSpacing.listRowIconGap else AppSpacing.m
            HorizontalDivider(
                modifier = Modifier.padding(start = dividerInset),
                color = DeXStudioTheme.appColors.separator,
                thickness = 0.5.dp
            )
        }
    }
}
