package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.AppToggle
import com.dexstudio.core.sharedui.designsystem.component.ListRow
import com.dexstudio.core.sharedui.designsystem.component.ListSection
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing

@Composable
fun ListGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeXStudioTheme.appColors.systemGroupedBackground) // Must be shown on grouped background
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Lists",
                style = DeXStudioTheme.textStyles.largeTitle,
                color = DeXStudioTheme.appColors.label
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Grouped list style with standard insets and hairlines.",
                style = DeXStudioTheme.textStyles.body,
                color = DeXStudioTheme.appColors.secondaryLabel
            )
        }

        Spacer(modifier = Modifier.height(AppSpacing.sectionGap))

        var pushToggle by remember { mutableStateOf(true) }

        ListSection(
            title = "Notifications",
            footer = "Choose whether to receive push notifications on your device."
        ) {
            ListRow(
                title = "Push Notifications",
                showDivider = true,
                trailingContent = {
                    AppToggle(
                        checked = pushToggle,
                        onCheckedChange = { pushToggle = it }
                    )
                }
            )
            ListRow(
                title = "Email Notifications",
                subtitle = "Weekly digest",
                showChevron = true,
                showDivider = false,
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(AppSpacing.sectionGap))

        ListSection(
            title = "Account"
        ) {
            ListRow(
                title = "Profile",
                value = "dex@example.com",
                showChevron = true,
                showDivider = true,
                onClick = {}
            )
            ListRow(
                title = "Subscriptions",
                showChevron = true,
                showDivider = false,
                onClick = {}
            )
        }
    }
}
