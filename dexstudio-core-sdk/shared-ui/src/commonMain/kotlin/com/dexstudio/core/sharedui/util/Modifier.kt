package com.dexstudio.core.sharedui.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.animation.AppAnimations
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.GlassVariant
import com.dexstudio.core.sharedui.designsystem.token.glassColor
import com.dexstudio.core.sharedui.designsystem.token.glassBorderColor

// =============================================================================
// Modifier Extensions — DESIGN.md §1, §6, §7
// =============================================================================

/**
 * iOS-style press scale animation.
 *
 * Scales the element to 0.95× on press and back to 1.0× on release,
 * using the [AppAnimations.pressScaleSpring] spring spec (DESIGN.md §7).
 */
@Composable
fun Modifier.pressScale(
    onClick: () -> Unit,
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1.0f,
        animationSpec = AppAnimations.pressScaleSpring,
        label = "PressScale",
    )

    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        )
}

/**
 * Applies a Liquid Glass background (DESIGN.md §1.2).
 *
 * Uses the glass fill color for the given [variant] and applies the
 * specular border. For full blur, the parent must set up Haze.
 *
 * @param variant The glass variant to use (default: Regular)
 */
@Composable
fun Modifier.glassBg(variant: GlassVariant = GlassVariant.Regular): Modifier {
    val fillColor = glassColor(variant)
    val borderColor = glassBorderColor()
    val shape = DeXStudioTheme.appShapes.large

    return this
        .background(fillColor, shape)
        .border(0.5.dp, borderColor, shape)
}

/**
 * Creates a pill-shaped transparent background — used for pill buttons
 * that need the shape but no fill.
 */
@Composable
fun Modifier.pillShape(): Modifier {
    return this.background(
        color = Color.Transparent,
        shape = DeXStudioTheme.appShapes.pill,
    )
}

/**
 * iOS-style layered shadow (DESIGN.md §1.2).
 *
 * Softer and more diffused than Android's default Material shadow.
 * Uses Black at 15–20% opacity.
 *
 * @param elevation Shadow spread (default 10dp)
 */
@Composable
fun Modifier.iosShadow(elevation: Dp = 10.dp): Modifier {
    return this.shadow(
        elevation = elevation,
        shape = DeXStudioTheme.appShapes.standard,
        clip = false,
        ambientColor = Color(0x26000000), // Black at 15%
        spotColor = Color(0x33000000),    // Black at 20%
    )
}

/**
 * Card-level shadow for elevated content cards.
 *
 * Note: iOS grouped lists do NOT use shadows. This is only for
 * cards that explicitly need elevation (media cards, floating panels).
 */
@Composable
fun Modifier.cardShadow(): Modifier {
    return this.shadow(
        elevation = 30.dp,
        shape = DeXStudioTheme.appShapes.standard,
        clip = false,
        ambientColor = Color(0x38000000),
        spotColor = Color(0x38000000),
    )
}
