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
import com.dexstudio.core.sharedui.designsystem.component.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Cards",
            style = DeXStudioTheme.textStyles.displayHero,
            color = DeXStudioTheme.appColors.textPrimary,
            modifier = Modifier.semantics { contentDescription = "Cards Components Heading" }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "ProductCard and ProductGridTile components.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(modifier = Modifier.widthIn(min = 280.dp, max = 500.dp).weight(1f)) {
                Text(
                    text = "ProductCard (Light)",
                    style = DeXStudioTheme.textStyles.bodyEmphasis,
                    color = DeXStudioTheme.appColors.textPrimary,
                    modifier = Modifier.semantics { contentDescription = "ProductCard Example" }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCard(
                    image = {
                        Box(modifier = Modifier.fillMaxSize().background(DeXStudioTheme.appColors.surfaceElevated2, DeXStudioTheme.appShapes.standard))
                    },
                    title = "MacBook Pro",
                    description = "Mind-blowing. Head-turning.",
                    actions = {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            PrimaryCTA(text = "Buy", onClick = {})
                            PillLink(text = "Learn more", onClick = {})
                        }
                    },
                    modifier = Modifier.heightIn(min = 300.dp)
                )
            }
            
            Column(modifier = Modifier.widthIn(min = 250.dp, max = 400.dp).weight(1f)) {
                Text(
                    text = "ProductGridTile (Light)",
                    style = DeXStudioTheme.textStyles.bodyEmphasis,
                    color = DeXStudioTheme.appColors.textPrimary,
                    modifier = Modifier.semantics { contentDescription = "ProductGridTile Example" }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductGridTile(
                    image = {
                        Box(modifier = Modifier.fillMaxSize().background(DeXStudioTheme.appColors.surfaceElevated2, DeXStudioTheme.appShapes.standard))
                    },
                    title = "iPhone 15 Pro",
                    description = "Titanium. So strong. So light. So Pro.",
                    actions = {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            PrimaryCTA(text = "Buy", onClick = {})
                        }
                    }
                )
            }
        }
    }
}
