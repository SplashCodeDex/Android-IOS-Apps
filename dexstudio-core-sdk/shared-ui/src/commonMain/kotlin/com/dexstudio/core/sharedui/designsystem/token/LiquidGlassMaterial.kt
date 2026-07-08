package com.dexstudio.core.sharedui.designsystem.token

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

// =============================================================================
// Liquid Glass Material System — DESIGN.md §1
// =============================================================================

/**
 * Glass variants as described in DESIGN.md §1.3.
 *
 * Liquid Glass is Apple's translucent, depth-aware material system.
 * Each variant controls the opacity of the fill layer above the
 * blurred backdrop.
 */
enum class GlassVariant {
    /** ~55% fill — Navigation bars, tab bars, toolbars */
    Regular,

    /** ~25% fill — Small floating controls over rich imagery */
    Clear,

    /** ~72% fill — Keyboards, alerts, action sheets (maximum legibility) */
    Thick,

    /** 0% / disabled — When glass effect should be toggled off entirely */
    Identity,
}

/**
 * Whether the user has enabled "Reduce Transparency" accessibility setting.
 *
 * When `true`, all glass materials should degrade to opaque
 * [secondarySystemBackground] fills with no blur and no transparency
 * (DESIGN.md §1.4).
 */
val LocalReduceTransparency = staticCompositionLocalOf { false }

/**
 * Retrieves the glass fill color for the given [variant] from the current
 * theme's color scheme.
 *
 * If [LocalReduceTransparency] is `true`, returns [secondarySystemBackground]
 * regardless of variant (DESIGN.md §1.4).
 */
@Composable
fun glassColor(variant: GlassVariant): Color {
    val reduceTransparency = LocalReduceTransparency.current
    if (reduceTransparency) {
        return DeXStudioTheme.appColors.secondarySystemBackground
    }
    return when (variant) {
        GlassVariant.Regular -> DeXStudioTheme.appColors.glassRegular
        GlassVariant.Clear -> DeXStudioTheme.appColors.glassClear
        GlassVariant.Thick -> DeXStudioTheme.appColors.glassThick
        GlassVariant.Identity -> Color.Transparent
    }
}

/**
 * Retrieves the specular border color, or [separator] when Reduce
 * Transparency is enabled.
 */
@Composable
fun glassBorderColor(): Color {
    val reduceTransparency = LocalReduceTransparency.current
    return if (reduceTransparency) {
        DeXStudioTheme.appColors.separator
    } else {
        DeXStudioTheme.appColors.glassSpecularBorder
    }
}

/**
 * A composable container that applies Liquid Glass styling.
 *
 * This is the primary building block for all floating functional UI:
 * nav bars, tab bars, toolbars, alerts, sheets, context menus, etc.
 *
 * The glass effect is composed of:
 * 1. Semi-transparent background fill (variant-dependent opacity)
 * 2. Thin specular border (0.5dp, 8–12% opacity white/black)
 * 3. Subtle shadow for elevation
 * 4. Continuous corner clipping
 *
 * Note: True backdrop blur requires the Haze library modifiers applied
 * to the content-behind. This container handles the overlay styling;
 * the parent layout must set up [HazeSource] / [hazeEffect] for blur.
 *
 * @param variant The glass opacity variant (Regular, Clear, Thick, Identity)
 * @param shape Corner shape for the glass container
 * @param elevation Shadow elevation (default 4dp for subtle iOS shadow)
 * @param modifier Additional modifiers
 * @param content Content inside the glass container
 */
@Composable
fun GlassContainer(
    variant: GlassVariant = GlassVariant.Regular,
    shape: RoundedCornerShape = DeXStudioTheme.appShapes.large,
    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val fillColor = glassColor(variant)
    val borderColor = glassBorderColor()
    val reduceTransparency = LocalReduceTransparency.current

    val shadowColor = if (reduceTransparency) {
        Color.Transparent
    } else {
        Color(0x33000000) // Black at 20%
    }

    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                clip = false,
                ambientColor = shadowColor,
                spotColor = shadowColor,
            )
            .clip(shape)
            .background(fillColor, shape)
            .border(0.5.dp, borderColor, shape),
        content = content,
    )
}
