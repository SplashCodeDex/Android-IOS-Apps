package com.dexstudio.core.sharedui.designsystem.token

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// iOS Dynamic Type Scale
data class AppTextStyles(
    val largeTitle: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val headline: TextStyle,
    val body: TextStyle,
    val callout: TextStyle,
    val subhead: TextStyle,
    val footnote: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,

    // Legacy names mapped for compatibility during migration
    val displayHero: TextStyle,
    val sectionHeading: TextStyle,
    val tileHeading: TextStyle,
    val cardTitle: TextStyle,
    val subHeading: TextStyle,
    val navHeading: TextStyle,
    val subNav: TextStyle,
    val bodyEmphasis: TextStyle,
    val buttonLarge: TextStyle,
    val button: TextStyle,
    val link: TextStyle,
    val caption: TextStyle,
    val captionBold: TextStyle,
    val micro: TextStyle,
    val microBold: TextStyle,
    val nano: TextStyle
)

@Composable
fun getAppTextStyles(): AppTextStyles {
    val systemFont = FontFamily.Default
    
    // iOS Dynamic Type specifications
    val largeTitleStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 34.sp, lineHeight = 41.sp, letterSpacing = 0.37.sp)
    val title1Style = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 28.sp, lineHeight = 34.sp, letterSpacing = 0.36.sp)
    val title2Style = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.35.sp)
    val title3Style = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 20.sp, lineHeight = 25.sp, letterSpacing = 0.38.sp)
    val headlineStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.41).sp)
    val bodyStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.41).sp)
    val calloutStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 21.sp, letterSpacing = (-0.32).sp)
    val subheadStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 20.sp, letterSpacing = (-0.24).sp)
    val footnoteStyle = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = (-0.08).sp)
    val caption1Style = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.sp)
    val caption2Style = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 13.sp, letterSpacing = 0.07.sp)

    return AppTextStyles(
        largeTitle = largeTitleStyle,
        title1 = title1Style,
        title2 = title2Style,
        title3 = title3Style,
        headline = headlineStyle,
        body = bodyStyle,
        callout = calloutStyle,
        subhead = subheadStyle,
        footnote = footnoteStyle,
        caption1 = caption1Style,
        caption2 = caption2Style,
        
        // Legacy maps
        displayHero = largeTitleStyle,
        sectionHeading = title1Style,
        tileHeading = title2Style,
        cardTitle = headlineStyle,
        subHeading = subheadStyle,
        navHeading = largeTitleStyle,
        subNav = title2Style,
        bodyEmphasis = headlineStyle,
        buttonLarge = headlineStyle,
        button = bodyStyle,
        link = calloutStyle,
        caption = caption1Style,
        captionBold = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.sp),
        micro = caption2Style,
        microBold = TextStyle(fontFamily = systemFont, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, lineHeight = 13.sp, letterSpacing = 0.07.sp),
        nano = caption2Style
    )
}

val LocalAppTextStyles = staticCompositionLocalOf<AppTextStyles> {
    error("No AppTextStyles provided")
}

@Composable
fun getM3Typography(appTextStyles: AppTextStyles) = Typography(
    displayLarge = appTextStyles.largeTitle,
    displayMedium = appTextStyles.title1,
    displaySmall = appTextStyles.title2,
    headlineMedium = appTextStyles.headline,
    titleLarge = appTextStyles.title3,
    bodyLarge = appTextStyles.body,
    bodyMedium = appTextStyles.callout,
    labelSmall = appTextStyles.caption1
)
