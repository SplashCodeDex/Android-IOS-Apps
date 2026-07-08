package com.dexstudio.core.sharedui.catalog.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.ContextMenu
import com.dexstudio.core.sharedui.designsystem.component.ContextMenuItem
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

@Composable
fun ContextMenuGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 40.dp)) {
            Text(
                text = "Context Menus",
                style = DeXStudioTheme.textStyles.largeTitle,
                color = DeXStudioTheme.appColors.label
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Floating glass panels with standard and destructive actions.",
                style = DeXStudioTheme.textStyles.body,
                color = DeXStudioTheme.appColors.secondaryLabel
            )
        }

        // We wrap the context menu in a box to simulate how it looks floating over content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(DeXStudioTheme.appColors.systemBlue, DeXStudioTheme.appShapes.standard)
                .padding(32.dp)
        ) {
            ContextMenu(
                items = listOf(
                    ContextMenuItem(label = "Reply", onClick = {}),
                    ContextMenuItem(label = "Forward", onClick = {}),
                    ContextMenuItem(label = "Copy Link", onClick = {}),
                    ContextMenuItem(label = "Delete", isDestructive = true, onClick = {})
                )
            )
        }
    }
}
