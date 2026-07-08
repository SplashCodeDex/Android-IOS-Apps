package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing
import com.dexstudio.core.sharedui.util.cardShadow

/**
 * iOS 27 Style Floating Product Card
 *
 * Uses secondarySystemGroupedBackground for the card surface.
 */
@Composable
fun ProductCard(
    image: @Composable () -> Unit,
    title: String,
    description: String,
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .cardShadow()
            .background(DeXStudioTheme.appColors.secondarySystemGroupedBackground, DeXStudioTheme.appShapes.standard)
            .padding(AppSpacing.l)
    ) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            image()
        }
        Spacer(modifier = Modifier.height(AppSpacing.m))
        Text(
            text = title,
            style = DeXStudioTheme.textStyles.headline,
            color = DeXStudioTheme.appColors.label
        )
        Spacer(modifier = Modifier.height(AppSpacing.xs))
        Text(
            text = description,
            style = DeXStudioTheme.textStyles.caption1,
            color = DeXStudioTheme.appColors.secondaryLabel
        )
        Spacer(modifier = Modifier.height(AppSpacing.m))
        actions()
    }
}

/**
 * iOS 27 Style Grid Tile
 */
@Composable
fun ProductGridTile(
    image: @Composable () -> Unit,
    title: String,
    description: String,
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .background(DeXStudioTheme.appColors.secondarySystemGroupedBackground, DeXStudioTheme.appShapes.standard)
            .padding(AppSpacing.m)
    ) {
        Box(modifier = Modifier.fillMaxWidth().weight(0.6f)) {
            image()
        }
        Spacer(modifier = Modifier.height(AppSpacing.s))
        Text(
            text = title,
            style = DeXStudioTheme.textStyles.title2,
            color = DeXStudioTheme.appColors.label
        )
        Spacer(modifier = Modifier.height(AppSpacing.xs))
        Text(
            text = description,
            style = DeXStudioTheme.textStyles.caption1,
            color = DeXStudioTheme.appColors.secondaryLabel
        )
        Spacer(modifier = Modifier.height(AppSpacing.s))
        actions()
    }
}
