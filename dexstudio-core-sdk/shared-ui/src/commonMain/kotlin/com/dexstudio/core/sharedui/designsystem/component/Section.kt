package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing

@Composable
fun HeroSection(
    title: String,
    subtitle: String,
    isDark: Boolean = false, // Kept for API compatibility, though theme handles dark mode natively
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // If explicitly forcing dark rhythm, use a secondary background, else use standard background
    val actualBgColor = if (isDark) DeXStudioTheme.appColors.secondarySystemGroupedBackground else DeXStudioTheme.appColors.systemGroupedBackground
    val actualTextColor = DeXStudioTheme.appColors.label

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(actualBgColor)
            .padding(vertical = AppSpacing.hero, horizontal = AppSpacing.l)
    ) {
        Text(
            text = title,
            style = DeXStudioTheme.textStyles.largeTitle,
            color = actualTextColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(AppSpacing.s))
        Text(
            text = subtitle,
            style = DeXStudioTheme.textStyles.title2,
            color = actualTextColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(AppSpacing.l))
        content()
    }
}
