package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(horizontal = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Color Palette",
                style = DeXStudioTheme.textStyles.largeTitle,
                color = DeXStudioTheme.appColors.label,
                modifier = Modifier.semantics { contentDescription = "Color Palette Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "iOS 27 Semantic tokens mapped to the current theme.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.secondaryLabel
        )
        Spacer(modifier = Modifier.height(40.dp))

        val colors = listOf(
            "Primary Action" to DeXStudioTheme.appColors.primaryAction,
            "System Blue" to DeXStudioTheme.appColors.systemBlue,
            "System Red" to DeXStudioTheme.appColors.systemRed,
            "System Green" to DeXStudioTheme.appColors.systemGreen,
            "Background" to DeXStudioTheme.appColors.background,
            "System Background" to DeXStudioTheme.appColors.systemBackground,
            "Secondary Sys Bg" to DeXStudioTheme.appColors.secondarySystemBackground,
            "Tertiary Sys Bg" to DeXStudioTheme.appColors.tertiarySystemBackground,
            "Grouped Bg" to DeXStudioTheme.appColors.systemGroupedBackground,
            "Sec Grouped Bg" to DeXStudioTheme.appColors.secondarySystemGroupedBackground,
            "Tert Grouped Bg" to DeXStudioTheme.appColors.tertiarySystemGroupedBackground,
            "Label" to DeXStudioTheme.appColors.label,
            "Secondary Label" to DeXStudioTheme.appColors.secondaryLabel,
            "Tertiary Label" to DeXStudioTheme.appColors.tertiaryLabel,
            "Quaternary Label" to DeXStudioTheme.appColors.quaternaryLabel,
            "Separator" to DeXStudioTheme.appColors.separator,
            "Opaque Separator" to DeXStudioTheme.appColors.opaqueSeparator,
            "Glass Regular" to DeXStudioTheme.appColors.glassRegular,
            "Glass Thick" to DeXStudioTheme.appColors.glassThick,
            "Glass Clear" to DeXStudioTheme.appColors.glassClear
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            colors.forEach { (name, color) ->
                ColorSwatch(
                    name = name,
                    color = color,
                    modifier = Modifier.widthIn(min = 120.dp, max = 200.dp)
                )
            }
        }
    }
}

@Composable
fun ColorSwatch(name: String, color: Color, modifier: Modifier = Modifier) {
    val hex = colorToHex(color)
    Column(modifier = modifier.semantics { contentDescription = "Color swatch for $name, hex value $hex" }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = DeXStudioTheme.textStyles.headline,
            color = DeXStudioTheme.appColors.label
        )
        Text(
            text = hex,
            style = DeXStudioTheme.textStyles.caption2,
            color = DeXStudioTheme.appColors.secondaryLabel
        )
    }
}

private fun colorToHex(color: Color): String {
    val r = (color.red * 255).toInt()
    val g = (color.green * 255).toInt()
    val b = (color.blue * 255).toInt()
    val a = (color.alpha * 255).toInt()
    
    // Hand-rolled hex string builder to avoid String.format (JVM only)
    val hexChars = "0123456789ABCDEF"
    fun toHex(value: Int): String {
        return "${hexChars[value shr 4 and 0x0F]}${hexChars[value and 0x0F]}"
    }
    
    return if (a == 255) {
        "#${toHex(r)}${toHex(g)}${toHex(b)}"
    } else {
        "#${toHex(a)}${toHex(r)}${toHex(g)}${toHex(b)}"
    }
}
