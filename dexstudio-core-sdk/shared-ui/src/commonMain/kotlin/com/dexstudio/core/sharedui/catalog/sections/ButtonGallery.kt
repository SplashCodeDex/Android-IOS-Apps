package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Buttons",
            style = DeXStudioTheme.textStyles.displayHero,
            color = DeXStudioTheme.appColors.textPrimary,
            modifier = Modifier.semantics { contentDescription = "Buttons Components Heading" }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Interactive button components.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ButtonShowcase("PrimaryCTA") {
                PrimaryCTA(text = "Primary Action", onClick = {})
            }
            
            ButtonShowcase("DarkCTA") {
                DarkCTA(text = "Secondary Action", onClick = {})
            }
            
            ButtonShowcase("PillLink (Light)") {
                PillLink(text = "Light Link", onClick = {})
            }
            
            ButtonShowcase("PillLink (Dark)", isDark = true) {
                PillLink(text = "Dark Link", onClick = {})
            }
            
            ButtonShowcase("FilterButton") {
                FilterButton(text = "Filter", onClick = {})
            }
            
            ButtonShowcase("MediaControlButton") {
                MediaControlButton(text = "Play", onClick = {})
            }
        }
    }
}

@Composable
fun ButtonShowcase(name: String, isDark: Boolean = false, content: @Composable () -> Unit) {
    val bgColor = if (isDark) DeXStudioTheme.appColors.secondarySystemGroupedBackground else Color.Transparent

    Column(
        modifier = Modifier.widthIn(min = 150.dp, max = 300.dp).semantics(mergeDescendants = true) {
            contentDescription = "Showcase for $name"
        }
    ) {
        Text(
            text = name,
            style = DeXStudioTheme.textStyles.bodyEmphasis,
            color = DeXStudioTheme.appColors.textPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Box(
            modifier = Modifier
                .background(bgColor, DeXStudioTheme.appShapes.standard)
                .padding(16.dp)
        ) {
            content()
        }
    }
}
