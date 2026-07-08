package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.AppToggle
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@Composable
fun ToggleGallery() {
    var toggle1 by remember { mutableStateOf(true) }
    var toggle2 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Toggles",
                style = DeXStudioTheme.textStyles.largeTitle,
                color = DeXStudioTheme.appColors.label,
                modifier = Modifier.semantics { contentDescription = "Toggles Gallery Heading" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "iOS 27 style toggles with spring physics and precise drop shadows.",
            style = DeXStudioTheme.textStyles.body,
            color = DeXStudioTheme.appColors.secondaryLabel
        )
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Active Toggle:", style = DeXStudioTheme.textStyles.body, color = DeXStudioTheme.appColors.label)
            AppToggle(
                checked = toggle1,
                onCheckedChange = { toggle1 = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Inactive Toggle:", style = DeXStudioTheme.textStyles.body, color = DeXStudioTheme.appColors.label)
            AppToggle(
                checked = toggle2,
                onCheckedChange = { toggle2 = it }
            )
        }
    }
}
