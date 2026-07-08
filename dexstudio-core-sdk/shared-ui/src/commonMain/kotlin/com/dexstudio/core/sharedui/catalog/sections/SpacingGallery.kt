package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing

@Composable
fun SpacingGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Spacing",
            style = DeXStudioTheme.textStyles.displayHero,
            color = DeXStudioTheme.appColors.textPrimary,
            modifier = Modifier.semantics { contentDescription = "Spacing Tokens Heading" }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "AppSpacing tokens defining margin and padding.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        val spacings = listOf(
            "xxs" to AppSpacing.xxs,
            "xs" to AppSpacing.xs,
            "s" to AppSpacing.s,
            "m" to AppSpacing.m,
            "l" to AppSpacing.l,
            "xl" to AppSpacing.xl,
            "xxl" to AppSpacing.xxl,
            "hero" to AppSpacing.hero,
            "sp2" to AppSpacing.sp2,
            "sp4" to AppSpacing.sp4,
            "sp5" to AppSpacing.sp5,
            "sp6" to AppSpacing.sp6,
            "sp7" to AppSpacing.sp7,
            "sp8" to AppSpacing.sp8,
            "sp9" to AppSpacing.sp9,
            "sp10" to AppSpacing.sp10,
            "sp11" to AppSpacing.sp11,
            "sp14" to AppSpacing.sp14,
            "sp15" to AppSpacing.sp15,
            "sp17" to AppSpacing.sp17,
            "sp20" to AppSpacing.sp20,
            "sp24" to AppSpacing.sp24
        )

        spacings.forEach { (name, value) ->
            SpacingSpecimen(name, value)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SpacingSpecimen(name: String, value: Dp) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {
            contentDescription = "Spacing token $name, value ${value.value} dp"
        }
    ) {
        Column(modifier = Modifier.width(100.dp)) {
            Text(
                text = name,
                style = DeXStudioTheme.textStyles.bodyEmphasis,
                color = DeXStudioTheme.appColors.textPrimary
            )
            Text(
                text = "${value.value} dp",
                style = DeXStudioTheme.textStyles.micro,
                color = DeXStudioTheme.appColors.textSecondary
            )
        }
        
        Box(
            modifier = Modifier
                .width(value)
                .height(24.dp)
                .background(DeXStudioTheme.appColors.primaryAction)
        )
    }
}
