package com.dexstudio.core.sharedui.catalog

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dexstudio.core.sharedui.designsystem.component.*
import com.dexstudio.core.sharedui.designsystem.theme.DeXStudioTheme
import com.dexstudio.core.sharedui.designsystem.token.AppSpacing
import com.dexstudio.core.sharedui.designsystem.token.GlassContainer
import com.dexstudio.core.sharedui.designsystem.token.GlassVariant
import com.dexstudio.core.sharedui.util.pressScale
import org.jetbrains.compose.resources.painterResource
// import dev.chrisbanes.haze.HazeState
// import dev.chrisbanes.haze.haze

/**
 * iOS 27 Experimental Hub
 *
 * This is the ultimate WWDC-style showcase combining Liquid Glass, 
 * micro-animations, semantic colors, and deep typography hierarchies.
 */
@Composable
fun ExperimentalHubApp() {
    
    // Smooth infinite breathing animation for background mesh
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mesh_scale"
    )

    // Background gradient mesh simulation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeXStudioTheme.appColors.systemGroupedBackground)
    ) {
        // Ambient Mesh Gradients (Animatable in a real app, static multi-stop brush here)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            DeXStudioTheme.appColors.primaryAction.copy(alpha = 0.15f),
                            Color.Transparent
                        ),
                        radius = 1200f
                    )
                )
        )

        // Main Scrollable Content wrapped in Haze
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.dp) // Space for floating tab bar
        ) {
            // Hero Section
            HubHeroSection()
            
            Spacer(modifier = Modifier.height(AppSpacing.xl))
            
            // Feature Grid
            HubFeatureGrid()
            
            Spacer(modifier = Modifier.height(AppSpacing.sectionGap))
            
            // Interactive List Section
            HubInteractiveList()
        }

        // Floating Tab Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = AppSpacing.l)
        ) {
            GlassNavigation(
                title = "Hub",
                navItems = listOf(
                    NavItem("Home", onClick = {}),
                    NavItem("Catalog", onClick = {}),
                    NavItem("Settings", onClick = {})
                )
            )
        }
    }
}

@Composable
private fun HubHeroSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, bottom = 40.dp)
    ) {
        // "WWDC" Style Badge
        GlassContainer(
            variant = GlassVariant.Regular,
            shape = DeXStudioTheme.appShapes.pill,
            modifier = Modifier.padding(bottom = AppSpacing.m)
        ) {
            Text(
                text = "LIQUID GLASS PREVIEW",
                style = DeXStudioTheme.textStyles.nano,
                color = DeXStudioTheme.appColors.secondaryLabel,
                modifier = Modifier.padding(horizontal = AppSpacing.m, vertical = AppSpacing.sp8)
            )
        }

        Text(
            text = "Welcome to iOS 27.",
            style = DeXStudioTheme.textStyles.displayHero,
            color = DeXStudioTheme.appColors.label
        )
        Spacer(modifier = Modifier.height(AppSpacing.s))
        Text(
            text = "An experimental hub combining fluid materials, dynamic typography, and profound depth.",
            style = DeXStudioTheme.textStyles.bodyEmphasis,
            color = DeXStudioTheme.appColors.secondaryLabel,
            modifier = Modifier.padding(horizontal = AppSpacing.xl)
        )
        
        Spacer(modifier = Modifier.height(AppSpacing.l))
        
        Row(horizontalArrangement = Arrangement.spacedBy(AppSpacing.m)) {
            PrimaryCTA(text = "Explore Features", onClick = {})
            DarkCTA(text = "View Source", onClick = {})
        }
    }
}

@Composable
private fun HubFeatureGrid() {
    Column(modifier = Modifier.padding(horizontal = AppSpacing.l)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.l)
        ) {
            // Left Tile
            ProductGridTile(
                image = {
                    Box(modifier = Modifier.fillMaxSize().background(DeXStudioTheme.appColors.systemBlue.copy(alpha = 0.1f), DeXStudioTheme.appShapes.standard))
                },
                title = "Lottie 3D",
                description = "Render volumetric models natively.",
                actions = { PillLink(text = "Try it", onClick = {}) },
                modifier = Modifier.weight(1f)
            )
            
            // Right Tile
            ProductGridTile(
                image = {
                    Box(modifier = Modifier.fillMaxSize().background(DeXStudioTheme.appColors.systemOrange.copy(alpha = 0.1f), DeXStudioTheme.appShapes.standard))
                },
                title = "Haze Blur",
                description = "Real-time frosted glass engine.",
                actions = { PillLink(text = "Try it", onClick = {}) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun HubInteractiveList() {
    var toggle by remember { mutableStateOf(true) }
    var selectedSegment by remember { mutableStateOf(0) }

    ListSection(
        title = "Component Playground",
        footer = "Interact with iOS 27 components dynamically rendering inside Compose Multiplatform."
    ) {
        ListRow(
            title = "Adaptive Glass",
            subtitle = "Translucency engine",
            trailingContent = {
                AppToggle(checked = toggle, onCheckedChange = { toggle = it })
            },
            showDivider = true
        )
        
        Box(modifier = Modifier.padding(horizontal = AppSpacing.m, vertical = AppSpacing.m)) {
            SegmentedControl(
                options = listOf("Thin", "Regular", "Thick"),
                selectedIndex = selectedSegment,
                onOptionSelected = { selectedSegment = it }
            )
        }
        
        ListRow(
            title = "Volume Intensity",
            showDivider = false,
            trailingContent = {
                // Inline slider in a row
                Box(modifier = Modifier.width(150.dp)) {
                    var sliderVal by remember { mutableStateOf(0.5f) }
                    AppSlider(value = sliderVal, onValueChange = { sliderVal = it })
                }
            }
        )
    }
}
