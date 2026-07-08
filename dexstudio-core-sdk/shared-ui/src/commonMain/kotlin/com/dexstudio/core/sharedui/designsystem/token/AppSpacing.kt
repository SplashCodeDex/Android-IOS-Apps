package com.dexstudio.core.sharedui.designsystem.token

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * iOS 27 Spacing Scale — DESIGN.md §5
 *
 * Base unit: 8pt grid. Everything aligns to multiples of 4pt
 * (half-grid for micro adjustments).
 */
object AppSpacing {
    // ── Core Scale ──────────────────────────────────────────────────────
    val xxs: Dp = 2.dp
    val xs: Dp = 4.dp
    val s: Dp = 8.dp
    val m: Dp = 16.dp
    val l: Dp = 20.dp    // iOS standard inset leading/trailing
    val xl: Dp = 24.dp
    val xxl: Dp = 32.dp
    val hero: Dp = 48.dp

    // ── Granular Steps (for pixel-precise micro-adjustments) ────────────
    val sp2: Dp = 2.dp
    val sp4: Dp = 4.dp
    val sp5: Dp = 5.dp
    val sp6: Dp = 6.dp
    val sp7: Dp = 7.dp
    val sp8: Dp = 8.dp
    val sp9: Dp = 9.dp
    val sp10: Dp = 10.dp
    val sp11: Dp = 11.dp
    val sp12: Dp = 12.dp
    val sp14: Dp = 14.dp
    val sp15: Dp = 15.dp
    val sp16: Dp = 16.dp
    val sp17: Dp = 17.dp
    val sp20: Dp = 20.dp
    val sp24: Dp = 24.dp
    val sp28: Dp = 28.dp
    val sp32: Dp = 32.dp
    val sp36: Dp = 36.dp

    // ── Semantic Aliases (DESIGN.md §5 — named by purpose) ──────────────

    /** Leading + trailing padding for inset grouped lists (20dp) */
    val listInsetHorizontal: Dp = 20.dp

    /** Top + bottom padding inside each list row (11dp) */
    val listRowVerticalPadding: Dp = 11.dp

    /** Space below section header text (6dp) */
    val sectionHeaderBottomPadding: Dp = 6.dp

    /** Standard tab bar component height (49dp) */
    val tabBarHeight: Dp = 49.dp

    /** Navigation bar height — inline (44dp) */
    val navBarHeight: Dp = 44.dp

    /** Search bar component height (36dp) */
    val searchBarHeight: Dp = 36.dp

    /** Bottom toolbar height (44dp) */
    val toolbarHeight: Dp = 44.dp

    /** Absolute minimum hit area for any interactive element (44dp) */
    val minimumTouchTarget: Dp = 44.dp

    /** Inset from screen edges for floating glass bars (8dp) */
    val glassBarEdgeInset: Dp = 8.dp

    /** Icon-to-title gap in list rows (12dp) */
    val listRowIconGap: Dp = 12.dp

    /** Leading icon rounded square size in list rows (29dp) */
    val listRowIconSize: Dp = 29.dp

    /** Section-to-section gap including header (35dp) */
    val sectionGap: Dp = 35.dp

    /** Gap between action sheet groups (8dp) */
    val actionSheetGroupGap: Dp = 8.dp

    /** Drag handle pill width (36dp) */
    val dragHandleWidth: Dp = 36.dp

    /** Drag handle pill height (5dp) */
    val dragHandleHeight: Dp = 5.dp
}
