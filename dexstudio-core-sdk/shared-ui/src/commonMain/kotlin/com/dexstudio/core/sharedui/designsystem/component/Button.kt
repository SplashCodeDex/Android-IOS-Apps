package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing
import com.dexstudio.core.sharedui.designsystem.token.GlassVariant
import com.dexstudio.core.sharedui.util.glassBg
import com.dexstudio.core.sharedui.util.pillShape
import com.dexstudio.core.sharedui.util.pressScale

/**
 * iOS 27 Primary Prominent Button (DESIGN.md §3.1)
 *
 * Solid semantic tint fill.
 * Used for primary actions (Save, Submit, Continue).
 */
@Composable
fun PrimaryCTA(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DeXStudioTheme.appColors.primaryAction,
            contentColor = DeXStudioTheme.appColors.onPrimaryAction
        ),
        shape = DeXStudioTheme.appShapes.standard,
        contentPadding = PaddingValues(horizontal = AppSpacing.m, vertical = AppSpacing.listRowVerticalPadding),
        modifier = modifier.pressScale(onClick = onClick)
    ) {
        Text(
            text = text,
            style = DeXStudioTheme.textStyles.headline,
            color = DeXStudioTheme.appColors.onPrimaryAction
        )
    }
}

/**
 * iOS 27 Tonal Button (DESIGN.md §3.2)
 *
 * Gray/Secondary fill.
 * Used for non-primary actions, filters, or cancel buttons.
 */
@Composable
fun DarkCTA(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DeXStudioTheme.appColors.secondarySystemGroupedBackground,
            contentColor = DeXStudioTheme.appColors.primaryAction
        ),
        shape = DeXStudioTheme.appShapes.standard,
        contentPadding = PaddingValues(horizontal = AppSpacing.m, vertical = AppSpacing.listRowVerticalPadding),
        modifier = modifier.pressScale(onClick = onClick)
    ) {
        Text(
            text = text,
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.primaryAction
        )
    }
}

/**
 * Pill-shaped outline link button.
 */
@Composable
fun PillLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val linkColor = DeXStudioTheme.appColors.link

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .pressScale(onClick = onClick)
            .pillShape()
            .border(1.dp, linkColor, DeXStudioTheme.appShapes.pill)
            .padding(horizontal = AppSpacing.sp14, vertical = AppSpacing.s)
    ) {
        Text(
            text = "$text >", // Emulated chevron
            style = DeXStudioTheme.textStyles.callout, // mapped to link previously
            color = linkColor
        )
    }
}

/**
 * Compact filter button (DESIGN.md §3.5 Pill / Capsule)
 */
@Composable
fun FilterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .pressScale(onClick = onClick)
            .background(DeXStudioTheme.appColors.secondarySystemGroupedBackground, DeXStudioTheme.appShapes.segmentedControl)
            .padding(horizontal = AppSpacing.sp14, vertical = AppSpacing.sp8)
    ) {
        Text(
            text = text,
            style = DeXStudioTheme.textStyles.subhead,
            color = DeXStudioTheme.appColors.label
        )
    }
}

/**
 * Circular glass control button (DESIGN.md §3.4 Glass Buttons)
 */
@Composable
fun MediaControlButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .pressScale(onClick = onClick)
            .glassBg(GlassVariant.Clear)
            .padding(AppSpacing.sp10)
    ) {
        Text(
            text = text,
            style = DeXStudioTheme.textStyles.subhead,
            color = DeXStudioTheme.appColors.label
        )
    }
}
