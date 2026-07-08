package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@Composable
fun SectionGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Sections",
                style = DeXStudioTheme.textStyles.displayHero,
                color = DeXStudioTheme.appColors.textPrimary,
                modifier = Modifier.semantics { contentDescription = "Sections Components Heading" }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "HeroSection component with Light and Dark variants.",
                style = DeXStudioTheme.textStyles.body,
                color = DeXStudioTheme.appColors.textSecondary
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.semantics(mergeDescendants = true) { contentDescription = "Light Hero Section" }) {
            HeroSection(
                title = "MacBook Air",
                subtitle = "Lean. Mean. M3 machine.",
                isDark = false
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PrimaryCTA(text = "Buy", onClick = {})
                    PillLink(text = "Learn more", onClick = {})
                }
            }
        }
        
        Box(modifier = Modifier.semantics(mergeDescendants = true) { contentDescription = "Dark Hero Section" }) {
            HeroSection(
                title = "iPad Pro",
                subtitle = "Supercharged by M4.",
                isDark = true
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PrimaryCTA(text = "Buy", onClick = {})
                    PillLink(text = "Learn more", onClick = {})
                }
            }
        }
    }
}
