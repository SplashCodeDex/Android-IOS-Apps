package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.animation.AppAnimations
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

/**
 * iOS 27 Toggle (Switch) Component — DESIGN.md §3.6
 *
 * Distinct from Android's Material Switch:
 * - Green (systemGreen) when active, opaqueSeparator/secondarySystemFill when inactive.
 * - Pill shape with a pure white circular knob.
 * - Knob has a distinct tight drop shadow.
 * - Knob compresses slightly when pressed (liquid feel).
 */
@Composable
fun AppToggle(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // iOS switch dimensions: 51x31
    val toggleWidth = 51.dp
    val toggleHeight = 31.dp
    val knobSize = 27.dp
    
    // Background color animation
    val targetBgColor = if (checked) {
        DeXStudioTheme.appColors.systemGreen
    } else {
        DeXStudioTheme.appColors.opaqueSeparator
    }
    
    val bgColor by animateColorAsState(
        targetValue = targetBgColor,
        label = "ToggleBgColor"
    )

    // Knob position animation (spring based)
    val targetOffset = if (checked) toggleWidth - knobSize - 2.dp else 2.dp
    val knobOffset by animateDpAsState(
        targetValue = targetOffset,
        label = "ToggleKnobOffset"
    )

    // Knob squash/stretch on press
    val knobWidthExpand = if (isPressed) 6.dp else 0.dp
    val currentKnobWidth by animateDpAsState(
        targetValue = knobSize + knobWidthExpand,
        label = "ToggleKnobWidth"
    )

    // Adjust offset when pressed and checked so it grows leftward, not rightward
    val adjustedOffset = if (checked && isPressed) {
        knobOffset - knobWidthExpand
    } else {
        knobOffset
    }

    Box(
        modifier = modifier
            .width(toggleWidth)
            .height(toggleHeight)
            .clip(DeXStudioTheme.appShapes.pill)
            .background(bgColor)
            .then(
                if (onCheckedChange != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onCheckedChange(!checked) }
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        // Knob
        Box(
            modifier = Modifier
                .offset(x = adjustedOffset)
                .width(currentKnobWidth)
                .height(knobSize)
                .shadow(
                    elevation = 2.dp,
                    shape = CircleShape,
                    ambientColor = Color(0x33000000),
                    spotColor = Color(0x33000000)
                )
                .background(Color.White, CircleShape)
        )
    }
}
