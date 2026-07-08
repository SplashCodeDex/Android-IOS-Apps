package com.dexstudio.core.sharedui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.catalog.sections.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme

enum class CatalogSection(val title: String) {
    COLORS("Colors"),
    TYPOGRAPHY("Typography"),
    SPACING("Spacing"),
    SHAPES("Shapes"),
    BUTTONS("Buttons"),
    CARDS("Cards"),
    SECTIONS("Sections"),
    NAVIGATION("Navigation"),
    TOGGLES("Toggles"),
    LISTS("Lists"),
    CONTEXT_MENU("Context Menu"),
    ANIMATIONS("Animations"),
    LOTTIE("Lottie")
}

@Composable
fun CatalogApp() {
    var isDarkTheme by remember { mutableStateOf(false) }
    var selectedSection by remember { mutableStateOf(CatalogSection.COLORS) }

    DeXStudioTheme(useDarkTheme = isDarkTheme) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(DeXStudioTheme.appColors.background)
                .semantics { testTag = "CatalogApp" }
        ) {
            val isCompact = maxWidth < 600.dp

            if (isCompact) {
                // Mobile Layout: Bottom Navigation
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = DeXStudioTheme.appColors.surfaceElevated2,
                            contentColor = DeXStudioTheme.appColors.textPrimary
                        ) {
                            CatalogSection.entries.take(5).forEach { section ->
                                NavigationBarItem(
                                    selected = selectedSection == section,
                                    onClick = { selectedSection = section },
                                    icon = { Text(section.title.take(2)) },
                                    label = { Text(section.title, style = DeXStudioTheme.textStyles.nano) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = DeXStudioTheme.appColors.primaryAction,
                                        selectedTextColor = DeXStudioTheme.appColors.primaryAction,
                                        indicatorColor = DeXStudioTheme.appColors.primaryAction.copy(alpha = 0.2f),
                                        unselectedIconColor = DeXStudioTheme.appColors.textSecondary,
                                        unselectedTextColor = DeXStudioTheme.appColors.textSecondary
                                    ),
                                    modifier = Modifier.semantics { contentDescription = "Navigate to ${section.title}" }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        ThemeToggleFab(isDarkTheme, { isDarkTheme = it }, Modifier.align(Alignment.BottomEnd).padding(16.dp))
                        CatalogContent(selectedSection)
                    }
                }
            } else {
                // Tablet/Desktop Layout: Navigation Rail or Sidebar
                Row(modifier = Modifier.fillMaxSize()) {
                    NavigationRail(
                        containerColor = DeXStudioTheme.appColors.surfaceElevated1,
                        contentColor = DeXStudioTheme.appColors.textPrimary,
                        modifier = Modifier.widthIn(max = 160.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                        ) {
                            CatalogSection.entries.forEach { section ->
                                NavigationRailItem(
                                    selected = selectedSection == section,
                                    onClick = { selectedSection = section },
                                    icon = { Text(section.title.take(2)) },
                                    label = { Text(section.title, style = DeXStudioTheme.textStyles.micro) },
                                    colors = NavigationRailItemDefaults.colors(
                                        selectedIconColor = DeXStudioTheme.appColors.primaryAction,
                                        selectedTextColor = DeXStudioTheme.appColors.primaryAction,
                                        indicatorColor = DeXStudioTheme.appColors.primaryAction.copy(alpha = 0.2f),
                                        unselectedIconColor = DeXStudioTheme.appColors.textSecondary,
                                        unselectedTextColor = DeXStudioTheme.appColors.textSecondary
                                    ),
                                    modifier = Modifier.semantics { contentDescription = "Navigate to ${section.title}" }
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth().padding(16.dp)
                        ) {
                            Text(if (isDarkTheme) "Dark" else "Light", style = DeXStudioTheme.textStyles.micro, color = DeXStudioTheme.appColors.textPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = { isDarkTheme = it },
                                modifier = Modifier.semantics { contentDescription = "Toggle Dark Theme" }
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.width(1.dp).fillMaxHeight(), color = DeXStudioTheme.appColors.surfaceElevated2)

                    Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                        CatalogContent(selectedSection)
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeToggleFab(isDarkTheme: Boolean, onToggle: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { onToggle(!isDarkTheme) },
        containerColor = DeXStudioTheme.appColors.primaryAction,
        contentColor = DeXStudioTheme.appColors.onPrimaryAction,
        modifier = modifier.semantics { contentDescription = "Toggle Theme Floating Button" }
    ) {
        Text(if (isDarkTheme) "Dark" else "Light")
    }
}

@Composable
private fun CatalogContent(section: CatalogSection) {
    when (section) {
        CatalogSection.COLORS -> ColorGallery()
        CatalogSection.TYPOGRAPHY -> TypographyGallery()
        CatalogSection.SPACING -> SpacingGallery()
        CatalogSection.SHAPES -> ShapesGallery()
        CatalogSection.BUTTONS -> ButtonGallery()
        CatalogSection.CARDS -> CardGallery()
        CatalogSection.SECTIONS -> SectionGallery()
        CatalogSection.NAVIGATION -> NavigationGallery()
        CatalogSection.TOGGLES -> ToggleGallery()
        CatalogSection.LISTS -> ListGallery()
        CatalogSection.CONTEXT_MENU -> ContextMenuGallery()
        CatalogSection.ANIMATIONS -> AnimationGallery()
        CatalogSection.LOTTIE -> LottieGallery()
    }
}
