package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.util.pressScale

@Composable
fun AnimationGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Animations",
            style = DeXStudioTheme.textStyles.displayHero,
            color = DeXStudioTheme.appColors.textPrimary,
            modifier = Modifier.semantics { contentDescription = "Animations Gallery Heading" }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Interaction and transition animations.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Modifier.pressScale()",
            style = DeXStudioTheme.textStyles.bodyEmphasis,
            color = DeXStudioTheme.appColors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Click and hold the box below to see the Spring scale effect.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Box(
            modifier = Modifier
                .sizeIn(minWidth = 150.dp, minHeight = 150.dp, maxWidth = 300.dp, maxHeight = 300.dp)
                .pressScale(onClick = {})
                .background(DeXStudioTheme.appColors.primaryAction, DeXStudioTheme.appShapes.standard)
                .semantics { contentDescription = "Press Scale animation interactive box" }
        )
    }
}
