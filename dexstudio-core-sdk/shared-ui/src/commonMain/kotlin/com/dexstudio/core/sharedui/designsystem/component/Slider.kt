package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

/**
 * iOS 27 Style Slider
 *
 * Distinctive thick track and prominent shadow knob.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0
) {
    val interactionSource = remember { MutableInteractionSource() }

    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        valueRange = valueRange,
        steps = steps,
        interactionSource = interactionSource,
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = DeXStudioTheme.appColors.primaryAction,
            inactiveTrackColor = DeXStudioTheme.appColors.opaqueSeparator
        ),
        thumb = {
            Box(
                modifier = Modifier
                    .size(28.dp) // iOS thumb size
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = Color(0x33000000),
                        spotColor = Color(0x33000000)
                    )
                    .background(Color.White, CircleShape)
            )
        },
        track = { sliderState ->
            val fraction = (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(DeXStudioTheme.appColors.opaqueSeparator, DeXStudioTheme.appShapes.pill)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(6.dp)
                        .background(DeXStudioTheme.appColors.primaryAction, DeXStudioTheme.appShapes.pill)
                )
            }
        }
    )
}
