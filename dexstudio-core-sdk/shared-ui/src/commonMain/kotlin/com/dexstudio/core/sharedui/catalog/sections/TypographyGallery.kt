package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@Composable
fun TypographyGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Typography",
                style = DeXStudioTheme.textStyles.displayHero,
                color = DeXStudioTheme.appColors.textPrimary,
                modifier = Modifier.semantics { contentDescription = "Typography Hierarchy Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Typography hierarchy defined in AppTypography.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        val styles = listOf(
            "Display Hero" to DeXStudioTheme.textStyles.displayHero,
            "Section Heading" to DeXStudioTheme.textStyles.sectionHeading,
            "Tile Heading" to DeXStudioTheme.textStyles.tileHeading,
            "Card Title" to DeXStudioTheme.textStyles.cardTitle,
            "Sub-heading" to DeXStudioTheme.textStyles.subHeading,
            "Nav Heading" to DeXStudioTheme.textStyles.navHeading,
            "Sub-nav" to DeXStudioTheme.textStyles.subNav,
            "Body" to DeXStudioTheme.textStyles.body,
            "Body Emphasis" to DeXStudioTheme.textStyles.bodyEmphasis,
            "Button Large" to DeXStudioTheme.textStyles.buttonLarge,
            "Button" to DeXStudioTheme.textStyles.button,
            "Link" to DeXStudioTheme.textStyles.link,
            "Caption" to DeXStudioTheme.textStyles.caption,
            "Caption Bold" to DeXStudioTheme.textStyles.captionBold,
            "Micro" to DeXStudioTheme.textStyles.micro,
            "Micro Bold" to DeXStudioTheme.textStyles.microBold,
            "Nano" to DeXStudioTheme.textStyles.nano
        )

        styles.forEach { (name, style) ->
            TypographySpecimen(name, style)
            HorizontalDivider(
                color = DeXStudioTheme.appColors.surfaceElevated2, 
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TypographySpecimen(name: String, style: TextStyle) {
    FlowRow(
        modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {
            contentDescription = "Typography Specimen for $name"
        },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(modifier = Modifier.widthIn(min = 150.dp, max = 250.dp)) {
            Text(
                text = name,
                style = DeXStudioTheme.textStyles.bodyEmphasis,
                color = DeXStudioTheme.appColors.textPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            val fontSize = if (style.fontSize.isUnspecified) "Default" else "${style.fontSize.value}sp"
            val lineHeight = if (style.lineHeight.isUnspecified) "Auto" else "${style.lineHeight.value}sp"
            val letterSpacing = if (style.letterSpacing.isUnspecified) "0" else "${style.letterSpacing.value}sp"
            
            Text(
                text = "Size: $fontSize",
                style = DeXStudioTheme.textStyles.micro,
                color = DeXStudioTheme.appColors.textSecondary
            )
            Text(
                text = "Weight: ${style.fontWeight?.weight ?: 400}",
                style = DeXStudioTheme.textStyles.micro,
                color = DeXStudioTheme.appColors.textSecondary
            )
            Text(
                text = "Line Height: $lineHeight",
                style = DeXStudioTheme.textStyles.micro,
                color = DeXStudioTheme.appColors.textSecondary
            )
            Text(
                text = "Letter Spacing: $letterSpacing",
                style = DeXStudioTheme.textStyles.micro,
                color = DeXStudioTheme.appColors.textSecondary
            )
        }
        
        Box(
            modifier = Modifier
                .weight(1f)
                .background(DeXStudioTheme.appColors.surfaceElevated1, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "The quick brown fox jumps over the lazy dog",
                style = style,
                color = DeXStudioTheme.appColors.textPrimary
            )
        }
    }
}
