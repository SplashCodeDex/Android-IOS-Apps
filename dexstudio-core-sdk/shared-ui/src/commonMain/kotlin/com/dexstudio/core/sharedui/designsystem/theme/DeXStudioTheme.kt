package com.dexstudio.core.sharedui.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.dexstudio.core.sharedui.designsystem.token.*

@Composable
fun DeXStudioTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    reduceTransparency: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (useDarkTheme) DarkAppColorScheme else LightAppColorScheme
    val textStyles = getAppTextStyles()
    val shapes = AppShapes()

    // Map iOS semantic colors → M3 color scheme slots.
    // M3 is the underlying Compose rendering engine; our AppColorScheme
    // is the source of truth, but M3 components need these mapped.
    val m3Colors = if (useDarkTheme) {
        darkColorScheme(
            primary = colorScheme.systemBlue,
            onPrimary = Color.White,
            secondary = colorScheme.systemGray5,
            onSecondary = colorScheme.label,
            tertiary = colorScheme.systemIndigo,
            onTertiary = Color.White,
            background = colorScheme.systemBackground,
            onBackground = colorScheme.label,
            surface = colorScheme.secondarySystemBackground,
            onSurface = colorScheme.label,
            surfaceVariant = colorScheme.tertiarySystemBackground,
            onSurfaceVariant = colorScheme.secondaryLabel,
            error = colorScheme.systemRed,
            onError = Color.White,
            outline = colorScheme.separator,
            outlineVariant = colorScheme.opaqueSeparator,
            surfaceContainerHighest = colorScheme.secondarySystemGroupedBackground,
        )
    } else {
        lightColorScheme(
            primary = colorScheme.systemBlue,
            onPrimary = Color.White,
            secondary = colorScheme.systemGray5,
            onSecondary = colorScheme.label,
            tertiary = colorScheme.systemIndigo,
            onTertiary = Color.White,
            background = colorScheme.systemBackground,
            onBackground = colorScheme.label,
            surface = colorScheme.secondarySystemBackground,
            onSurface = colorScheme.label,
            surfaceVariant = colorScheme.tertiarySystemBackground,
            onSurfaceVariant = colorScheme.secondaryLabel,
            error = colorScheme.systemRed,
            onError = Color.White,
            outline = colorScheme.separator,
            outlineVariant = colorScheme.opaqueSeparator,
            surfaceContainerHighest = colorScheme.secondarySystemGroupedBackground,
        )
    }

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTextStyles provides textStyles,
        LocalAppShapes provides shapes,
        LocalReduceTransparency provides reduceTransparency,
    ) {
        MaterialTheme(
            colorScheme = m3Colors,
            typography = getM3Typography(textStyles),
            content = content,
        )
    }
}

object DeXStudioTheme {
    val appColors: AppColorScheme
        @Composable
        get() = LocalAppColorScheme.current

    val textStyles: AppTextStyles
        @Composable
        get() = LocalAppTextStyles.current

    val appShapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
}

// Private import needed for Color.White literals in M3 mapping
private val Color = androidx.compose.ui.graphics.Color
