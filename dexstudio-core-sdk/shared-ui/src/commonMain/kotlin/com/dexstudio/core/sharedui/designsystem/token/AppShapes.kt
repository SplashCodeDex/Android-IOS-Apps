package com.dexstudio.core.sharedui.designsystem.token

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * iOS 27 Shape Scale — DESIGN.md §4
 *
 * iOS uses superellipse ("squircle") continuous corners. In Compose we
 * approximate with [RoundedCornerShape]. The visual intent is always a
 * smooth, continuous curve — not a standard CSS border-radius circle arc.
 */
data class AppShapes(
    /** 5dp — Keyboard individual keys */
    val micro: RoundedCornerShape = RoundedCornerShape(5.dp),

    /** 8dp — Small tags, compact buttons */
    val small: RoundedCornerShape = RoundedCornerShape(8.dp),

    /** 10dp — List section cards, grouped backgrounds, inset cards */
    val standard: RoundedCornerShape = RoundedCornerShape(10.dp),

    /** 12dp — Glass surfaces, buttons, glass small elements */
    val comfortable: RoundedCornerShape = RoundedCornerShape(12.dp),

    /** 14dp — Alerts, context menus */
    val medium: RoundedCornerShape = RoundedCornerShape(14.dp),

    /** 16dp — Glass cards, media cards, tab bar capsule */
    val large: RoundedCornerShape = RoundedCornerShape(16.dp),

    /** 22dp — Sheets, large modals */
    val xLarge: RoundedCornerShape = RoundedCornerShape(22.dp),

    /** 28dp — Full-screen sheets */
    val xxLarge: RoundedCornerShape = RoundedCornerShape(28.dp),

    /** 26dp — Search bar capsule */
    val searchBar: RoundedCornerShape = RoundedCornerShape(26.dp),

    /** 9dp — Segmented control outer + inner segment */
    val segmentedControl: RoundedCornerShape = RoundedCornerShape(9.dp),

    /** 980dp — Pill buttons (absurd radius → capsule) */
    val pill: RoundedCornerShape = RoundedCornerShape(980.dp),

    /** 50% — Circular buttons, toggle knobs, avatar clips */
    val circle: RoundedCornerShape = RoundedCornerShape(50),
)

val LocalAppShapes = staticCompositionLocalOf { AppShapes() }
