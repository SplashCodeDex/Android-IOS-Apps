package com.dexstudio.core.sharedui.designsystem.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// =============================================================================
// iOS 27 Semantic Color Tokens (DESIGN.md §2)
// =============================================================================

// --- §2.1 Background Colors ---

// Light
val SystemBackgroundLight = Color(0xFFFFFFFF)
val SecondarySystemBackgroundLight = Color(0xFFF2F2F7)
val TertiarySystemBackgroundLight = Color(0xFFFFFFFF)
val SystemGroupedBackgroundLight = Color(0xFFF2F2F7)
val SecondarySystemGroupedBackgroundLight = Color(0xFFFFFFFF)
val TertiarySystemGroupedBackgroundLight = Color(0xFFF2F2F7)

// Dark
val SystemBackgroundDark = Color(0xFF000000)
val SecondarySystemBackgroundDark = Color(0xFF1C1C1E)
val TertiarySystemBackgroundDark = Color(0xFF2C2C2E)
val SystemGroupedBackgroundDark = Color(0xFF000000)
val SecondarySystemGroupedBackgroundDark = Color(0xFF1C1C1E)
val TertiarySystemGroupedBackgroundDark = Color(0xFF2C2C2E)

// --- §2.2 Label (Text) Colors ---

val LabelLight = Color(0xFF000000)
val SecondaryLabelLight = Color(0x993C3C43)   // rgba(60,60,67, 0.60)
val TertiaryLabelLight = Color(0x4D3C3C43)    // rgba(60,60,67, 0.30)
val QuaternaryLabelLight = Color(0x2E3C3C43)  // rgba(60,60,67, 0.18)

val LabelDark = Color(0xFFFFFFFF)
val SecondaryLabelDark = Color(0x99EBEBF5)    // rgba(235,235,245, 0.60)
val TertiaryLabelDark = Color(0x4DEBEBF5)     // rgba(235,235,245, 0.30)
val QuaternaryLabelDark = Color(0x2EEBEBF5)   // rgba(235,235,245, 0.18)

// --- §2.3 Fill Colors (Interactive Surfaces) ---

val SystemFillLight = Color(0x33787880)       // rgba(120,120,128, 0.20)
val SecondarySystemFillLight = Color(0x29787880)  // rgba(120,120,128, 0.16)
val TertiarySystemFillLight = Color(0x1F787880)   // rgba(120,120,128, 0.12)
val QuaternarySystemFillLight = Color(0x14787880)  // rgba(120,120,128, 0.08)

val SystemFillDark = Color(0x5C787880)        // rgba(120,120,128, 0.36)
val SecondarySystemFillDark = Color(0x52787880)   // rgba(120,120,128, 0.32)
val TertiarySystemFillDark = Color(0x3D787880)    // rgba(120,120,128, 0.24)
val QuaternarySystemFillDark = Color(0x2E787880)  // rgba(120,120,128, 0.18)

// --- §2.4 Separator Colors ---

val SeparatorLight = Color(0x4A3C3C43)        // rgba(60,60,67, 0.29)
val OpaqueSeparatorLight = Color(0xFFC6C6C8)

val SeparatorDark = Color(0x99545458)         // rgba(84,84,88, 0.60)
val OpaqueSeparatorDark = Color(0xFF38383A)

// --- §2.5 Tint / Accent Colors ---

val SystemBlueLight = Color(0xFF007AFF)
val SystemBlueDark = Color(0xFF0A84FF)

val SystemRedLight = Color(0xFFFF3B30)
val SystemRedDark = Color(0xFFFF453A)

val SystemGreenLight = Color(0xFF34C759)
val SystemGreenDark = Color(0xFF30D158)

val SystemOrangeLight = Color(0xFFFF9500)
val SystemOrangeDark = Color(0xFFFF9F0A)

val SystemYellowLight = Color(0xFFFFCC00)
val SystemYellowDark = Color(0xFFFFD60A)

val SystemTealLight = Color(0xFF30B0C7)
val SystemTealDark = Color(0xFF40CBE0)

val SystemCyanLight = Color(0xFF32ADE6)
val SystemCyanDark = Color(0xFF64D2FF)

val SystemIndigoLight = Color(0xFF5856D6)
val SystemIndigoDark = Color(0xFF5E5CE6)

val SystemPurpleLight = Color(0xFFAF52DE)
val SystemPurpleDark = Color(0xFFBF5AF2)

val SystemPinkLight = Color(0xFFFF2D55)
val SystemPinkDark = Color(0xFFFF375F)

val SystemBrownLight = Color(0xFFA2845E)
val SystemBrownDark = Color(0xFFAC8E68)

val SystemMintLight = Color(0xFF00C7BE)
val SystemMintDark = Color(0xFF63E6E2)

// --- Gray Scale ---

val SystemGrayLight = Color(0xFF8E8E93)
val SystemGrayDark = Color(0xFF8E8E93)

val SystemGray2Light = Color(0xFFAEAEB2)
val SystemGray2Dark = Color(0xFF636366)

val SystemGray3Light = Color(0xFFC7C7CC)
val SystemGray3Dark = Color(0xFF48484A)

val SystemGray4Light = Color(0xFFD1D1D6)
val SystemGray4Dark = Color(0xFF3A3A3C)

val SystemGray5Light = Color(0xFFE5E5EA)
val SystemGray5Dark = Color(0xFF2C2C2E)

val SystemGray6Light = Color(0xFFF2F2F7)
val SystemGray6Dark = Color(0xFF1C1C1E)

// --- §1.2 Liquid Glass Material Colors (DESIGN.md §1.2) ---

val GlassRegularLight = Color(0x8CFFFFFF)  // White at ~55% opacity
val GlassRegularDark = Color(0x731C1C1E)   // Dark gray at ~45% opacity

val GlassClearLight = Color(0x40FFFFFF)    // White at ~25% opacity
val GlassClearDark = Color(0x331C1C1E)     // Dark gray at ~20% opacity

val GlassThickLight = Color(0xB8FFFFFF)    // White at ~72% opacity
val GlassThickDark = Color(0x991C1C1E)     // Dark gray at ~60% opacity

// Specular border (DESIGN.md §1.2: 0.5pt, 8-12% opacity)
val GlassSpecularBorderLight = Color(0x14000000)  // Black at 8%
val GlassSpecularBorderDark = Color(0x1FFFFFFF)   // White at 12%

// =============================================================================
// Color Scheme Data Class
// =============================================================================

data class AppColorScheme(
    // §2.1 Backgrounds
    val systemBackground: Color,
    val secondarySystemBackground: Color,
    val tertiarySystemBackground: Color,
    val systemGroupedBackground: Color,
    val secondarySystemGroupedBackground: Color,
    val tertiarySystemGroupedBackground: Color,

    // §2.2 Labels
    val label: Color,
    val secondaryLabel: Color,
    val tertiaryLabel: Color,
    val quaternaryLabel: Color,

    // §2.3 Fills
    val systemFill: Color,
    val secondarySystemFill: Color,
    val tertiarySystemFill: Color,
    val quaternarySystemFill: Color,

    // §2.4 Separators
    val separator: Color,
    val opaqueSeparator: Color,

    // §2.5 Tints
    val systemBlue: Color,
    val systemRed: Color,
    val systemGreen: Color,
    val systemOrange: Color,
    val systemYellow: Color,
    val systemTeal: Color,
    val systemCyan: Color,
    val systemIndigo: Color,
    val systemPurple: Color,
    val systemPink: Color,
    val systemBrown: Color,
    val systemMint: Color,

    // Gray scale
    val systemGray: Color,
    val systemGray2: Color,
    val systemGray3: Color,
    val systemGray4: Color,
    val systemGray5: Color,
    val systemGray6: Color,

    // §1.2 Liquid Glass
    val glassRegular: Color,
    val glassClear: Color,
    val glassThick: Color,
    val glassSpecularBorder: Color,

    // =========================================================================
    // Legacy compatibility mappings
    // These map old names used throughout the codebase to their iOS 27 semantic
    // equivalents so that existing code compiles without mass-refactoring.
    // =========================================================================
    val primaryAction: Color,
    val onPrimaryAction: Color,
    val secondaryAction: Color,
    val background: Color,
    val onBackground: Color,
    val surfaceDefault: Color,
    val surfaceElevated1: Color,
    val surfaceElevated2: Color,
    val onSurface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val link: Color,
    val linkOnDark: Color,
    val overlay: Color,
    val error: Color,
    val onError: Color,
    val navGlass: Color,
)

val LightAppColorScheme = AppColorScheme(
    // §2.1 Backgrounds
    systemBackground = SystemBackgroundLight,
    secondarySystemBackground = SecondarySystemBackgroundLight,
    tertiarySystemBackground = TertiarySystemBackgroundLight,
    systemGroupedBackground = SystemGroupedBackgroundLight,
    secondarySystemGroupedBackground = SecondarySystemGroupedBackgroundLight,
    tertiarySystemGroupedBackground = TertiarySystemGroupedBackgroundLight,

    // §2.2 Labels
    label = LabelLight,
    secondaryLabel = SecondaryLabelLight,
    tertiaryLabel = TertiaryLabelLight,
    quaternaryLabel = QuaternaryLabelLight,

    // §2.3 Fills
    systemFill = SystemFillLight,
    secondarySystemFill = SecondarySystemFillLight,
    tertiarySystemFill = TertiarySystemFillLight,
    quaternarySystemFill = QuaternarySystemFillLight,

    // §2.4 Separators
    separator = SeparatorLight,
    opaqueSeparator = OpaqueSeparatorLight,

    // §2.5 Tints
    systemBlue = SystemBlueLight,
    systemRed = SystemRedLight,
    systemGreen = SystemGreenLight,
    systemOrange = SystemOrangeLight,
    systemYellow = SystemYellowLight,
    systemTeal = SystemTealLight,
    systemCyan = SystemCyanLight,
    systemIndigo = SystemIndigoLight,
    systemPurple = SystemPurpleLight,
    systemPink = SystemPinkLight,
    systemBrown = SystemBrownLight,
    systemMint = SystemMintLight,

    // Gray scale
    systemGray = SystemGrayLight,
    systemGray2 = SystemGray2Light,
    systemGray3 = SystemGray3Light,
    systemGray4 = SystemGray4Light,
    systemGray5 = SystemGray5Light,
    systemGray6 = SystemGray6Light,

    // §1.2 Liquid Glass
    glassRegular = GlassRegularLight,
    glassClear = GlassClearLight,
    glassThick = GlassThickLight,
    glassSpecularBorder = GlassSpecularBorderLight,

    // Legacy compatibility
    primaryAction = SystemBlueLight,
    onPrimaryAction = Color.White,
    secondaryAction = SystemGray5Light,
    background = SystemGroupedBackgroundLight,
    onBackground = LabelLight,
    surfaceDefault = SecondarySystemGroupedBackgroundLight,
    surfaceElevated1 = SecondarySystemBackgroundLight,
    surfaceElevated2 = TertiarySystemBackgroundLight,
    onSurface = LabelLight,
    textPrimary = LabelLight,
    textSecondary = SecondaryLabelLight,
    textTertiary = TertiaryLabelLight,
    link = SystemBlueLight,
    linkOnDark = SystemCyanLight,
    overlay = Color(0x66000000),  // Black at 40%
    error = SystemRedLight,
    onError = Color.White,
    navGlass = GlassRegularLight,
)

val DarkAppColorScheme = AppColorScheme(
    // §2.1 Backgrounds
    systemBackground = SystemBackgroundDark,
    secondarySystemBackground = SecondarySystemBackgroundDark,
    tertiarySystemBackground = TertiarySystemBackgroundDark,
    systemGroupedBackground = SystemGroupedBackgroundDark,
    secondarySystemGroupedBackground = SecondarySystemGroupedBackgroundDark,
    tertiarySystemGroupedBackground = TertiarySystemGroupedBackgroundDark,

    // §2.2 Labels
    label = LabelDark,
    secondaryLabel = SecondaryLabelDark,
    tertiaryLabel = TertiaryLabelDark,
    quaternaryLabel = QuaternaryLabelDark,

    // §2.3 Fills
    systemFill = SystemFillDark,
    secondarySystemFill = SecondarySystemFillDark,
    tertiarySystemFill = TertiarySystemFillDark,
    quaternarySystemFill = QuaternarySystemFillDark,

    // §2.4 Separators
    separator = SeparatorDark,
    opaqueSeparator = OpaqueSeparatorDark,

    // §2.5 Tints
    systemBlue = SystemBlueDark,
    systemRed = SystemRedDark,
    systemGreen = SystemGreenDark,
    systemOrange = SystemOrangeDark,
    systemYellow = SystemYellowDark,
    systemTeal = SystemTealDark,
    systemCyan = SystemCyanDark,
    systemIndigo = SystemIndigoDark,
    systemPurple = SystemPurpleDark,
    systemPink = SystemPinkDark,
    systemBrown = SystemBrownDark,
    systemMint = SystemMintDark,

    // Gray scale
    systemGray = SystemGrayDark,
    systemGray2 = SystemGray2Dark,
    systemGray3 = SystemGray3Dark,
    systemGray4 = SystemGray4Dark,
    systemGray5 = SystemGray5Dark,
    systemGray6 = SystemGray6Dark,

    // §1.2 Liquid Glass
    glassRegular = GlassRegularDark,
    glassClear = GlassClearDark,
    glassThick = GlassThickDark,
    glassSpecularBorder = GlassSpecularBorderDark,

    // Legacy compatibility
    primaryAction = SystemBlueDark,
    onPrimaryAction = Color.White,
    secondaryAction = SystemGray5Dark,
    background = SystemGroupedBackgroundDark,
    onBackground = LabelDark,
    surfaceDefault = SecondarySystemGroupedBackgroundDark,
    surfaceElevated1 = SecondarySystemBackgroundDark,
    surfaceElevated2 = TertiarySystemBackgroundDark,
    onSurface = LabelDark,
    textPrimary = LabelDark,
    textSecondary = SecondaryLabelDark,
    textTertiary = TertiaryLabelDark,
    link = SystemBlueDark,
    linkOnDark = SystemCyanDark,
    overlay = Color(0x66000000),  // Black at 40%
    error = SystemRedDark,
    onError = Color.White,
    navGlass = GlassRegularDark,
)

val LocalAppColorScheme = staticCompositionLocalOf { LightAppColorScheme }
