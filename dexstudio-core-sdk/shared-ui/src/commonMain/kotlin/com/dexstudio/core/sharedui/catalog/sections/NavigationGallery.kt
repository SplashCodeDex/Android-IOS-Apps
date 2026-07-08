package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@Composable
fun NavigationGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Navigation",
                style = DeXStudioTheme.textStyles.displayHero,
                color = DeXStudioTheme.appColors.textPrimary,
                modifier = Modifier.semantics { contentDescription = "Navigation Examples Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "GlassNavigation component with blur effect.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(DeXStudioTheme.appColors.systemGroupedBackground)
                .semantics(mergeDescendants = true) { contentDescription = "Glass Navigation Bar preview over sample content" }
        ) {
            // Simulated content underneath to show the blur effect
            Box(modifier = Modifier.fillMaxSize().padding(top = 100.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    Box(modifier = Modifier.size(80.dp).background(DeXStudioTheme.appColors.systemBlue))
                    Box(modifier = Modifier.size(80.dp).background(DeXStudioTheme.appColors.secondarySystemGroupedBackground))
                    Box(modifier = Modifier.size(80.dp).background(DeXStudioTheme.appColors.secondarySystemBackground))
                }
            }

            // The navigation over it
            GlassNavigation(
                title = "DeXStudio",
                navItems = listOf(
                    NavItem("Store") {},
                    NavItem("Mac") {},
                    NavItem("iPad") {},
                    NavItem("iPhone") {},
                    NavItem("Watch") {},
                    NavItem("Support") {}
                )
            )
        }
    }
}
