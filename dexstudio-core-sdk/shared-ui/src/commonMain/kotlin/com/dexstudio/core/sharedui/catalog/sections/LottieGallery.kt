package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.DeXLottieAnimation
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LottieGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Lottie Animations",
                style = DeXStudioTheme.textStyles.displayHero,
                color = DeXStudioTheme.appColors.textPrimary,
                modifier = Modifier.semantics { contentDescription = "Lottie Animations Gallery Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Using Compottie for KMP Lottie rendering.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.textSecondary
        )
        Spacer(modifier = Modifier.height(40.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            LottieShowcase("Loading", "lottie_loading.json")
            LottieShowcase("Success", "lottie_success.json")
            LottieShowcase("Empty State", "lottie_empty_state.json")
        }
    }
}

@Composable
fun LottieShowcase(title: String, fileName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.semantics(mergeDescendants = true) {
            contentDescription = "Lottie animation showcase for $title"
        }
    ) {
        Text(
            text = title,
            style = DeXStudioTheme.textStyles.bodyEmphasis,
            color = DeXStudioTheme.appColors.textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        DeXLottieAnimation(
            resourcePath = fileName,
            modifier = Modifier.sizeIn(minWidth = 100.dp, minHeight = 100.dp, maxWidth = 200.dp, maxHeight = 200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = fileName,
            style = DeXStudioTheme.textStyles.micro,
            color = DeXStudioTheme.appColors.textSecondary
        )
    }
}
