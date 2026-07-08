package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.animation.AppAnimations
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing

/**
 * iOS 27 Segmented Control (DESIGN.md §3.6)
 *
 * Sliding pill background, separated by hairlines (when not active).
 */
@Composable
fun SegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var segmentWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(DeXStudioTheme.appColors.secondarySystemGroupedBackground, DeXStudioTheme.appShapes.segmentedControl)
            .padding(2.dp)
            .onSizeChanged { size ->
                if (options.isNotEmpty()) {
                    segmentWidth = with(density) { (size.width / options.size).toDp() }
                }
            }
    ) {
        // Sliding indicator
        if (segmentWidth > 0.dp) {
            val indicatorOffset by animateDpAsState(
                targetValue = segmentWidth * selectedIndex,
                label = "SegmentedControlIndicator"
            )

            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .width(segmentWidth)
                    .fillMaxHeight()
                    .shadow(
                        elevation = 2.dp,
                        shape = DeXStudioTheme.appShapes.segmentedControl,
                        ambientColor = Color(0x22000000),
                        spotColor = Color(0x22000000)
                    )
                    .background(DeXStudioTheme.appColors.background, DeXStudioTheme.appShapes.segmentedControl)
            )
        }

        // Text Labels
        Row(modifier = Modifier.fillMaxSize()) {
            options.forEachIndexed { index, option ->
                val interactionSource = remember { MutableInteractionSource() }
                val isSelected = index == selectedIndex
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(DeXStudioTheme.appShapes.segmentedControl)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { onOptionSelected(index) }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        style = DeXStudioTheme.textStyles.subhead,
                        color = if (isSelected) DeXStudioTheme.appColors.label else DeXStudioTheme.appColors.secondaryLabel
                    )
                }
            }
        }
    }
}
