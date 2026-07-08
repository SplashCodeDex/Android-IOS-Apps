package com.dexstudio.core.sharedui.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset

/**
 * iOS 27 Animation Specifications — DESIGN.md §7
 *
 * All animations use spring physics (not linear/ease curves) unless noted.
 * iOS 27's "liquid spring" system gives UI a gel-like, responsive feel.
 */
object AppAnimations {

    // ── General ─────────────────────────────────────────────────────────

    /** General glass morphing, element transitions (damping 0.78, stiffness 350) */
    val liquidSpring = spring<Float>(
        dampingRatio = 0.78f,
        stiffness = 350f
    )

    /** Button press/release (scale 1.0 → 0.95 → 1.0) */
    val pressScaleSpring = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    // ── Component-Specific Springs ──────────────────────────────────────

    /** Tab bar item switching */
    val tabBarTransition = spring<Float>(
        dampingRatio = 0.80f,
        stiffness = 400f
    )

    /** Sheet presentation and dismissal */
    val sheetSlide = spring<Float>(
        dampingRatio = 0.85f,
        stiffness = 300f
    )

    /** Segmented control indicator sliding */
    val segmentSlide = spring<Float>(
        dampingRatio = 0.70f,
        stiffness = 450f
    )

    /** Context menu pop-in from anchor */
    val contextMenuScale = spring<Float>(
        dampingRatio = 0.68f,
        stiffness = 500f
    )

    /** Toggle knob slide left ↔ right */
    val toggleKnob = spring<Float>(
        dampingRatio = 0.78f,
        stiffness = 350f
    )

    /** Cancel button slide-in on search focus */
    val searchCancel = spring<Float>(
        dampingRatio = 0.80f,
        stiffness = 400f
    )

    /** Notification banner drop from top */
    val notificationDrop = spring<IntOffset>(
        dampingRatio = 0.80f,
        stiffness = 350f
    )

    /** Edit menu appearance */
    val editMenuAppear = spring<Float>(
        dampingRatio = 0.72f,
        stiffness = 400f
    )

    // ── Tween Specs ─────────────────────────────────────────────────────

    /** Button press glow effect */
    val glassGlow = tween<Float>(durationMillis = 200)

    /** Generic fade entrance */
    val fadeInSpec = tween<Float>(durationMillis = 300)

    /** List row press highlight */
    val rowHighlight = tween<Float>(durationMillis = 200)

    // ── Slide-Up Content Entrance ───────────────────────────────────────

    val slideUpSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
}
