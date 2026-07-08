package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShapesGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Shapes",
                style = DeXStudioTheme.textStyles.displayHero,
                color = DeXStudioTheme.appColors.textPrimary,
                modifier = Modifier.semantics { contentDescription = "Shapes Gallery Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "iOS 27 shape scales defined in AppShapes.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        val shapes = listOf(
            "Micro (5dp)" to DeXStudioTheme.appShapes.micro,
            "Small (8dp)" to DeXStudioTheme.appShapes.small,
            "Standard (10dp)" to DeXStudioTheme.appShapes.standard,
            "Comfortable (12dp)" to DeXStudioTheme.appShapes.comfortable,
            "Medium (14dp)" to DeXStudioTheme.appShapes.medium,
            "Large (16dp)" to DeXStudioTheme.appShapes.large,
            "X-Large (22dp)" to DeXStudioTheme.appShapes.xLarge,
            "XX-Large (28dp)" to DeXStudioTheme.appShapes.xxLarge,
            "Search Bar (26dp)" to DeXStudioTheme.appShapes.searchBar,
            "Segment (9dp)" to DeXStudioTheme.appShapes.segmentedControl,
            "Pill (980dp)" to DeXStudioTheme.appShapes.pill,
            "Circle (50%)" to DeXStudioTheme.appShapes.circle
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            shapes.forEach { (name, shape) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(120.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(DeXStudioTheme.appColors.primaryAction, shape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = name,
                        style = DeXStudioTheme.textStyles.bodyEmphasis,
                        color = DeXStudioTheme.appColors.textPrimary,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
