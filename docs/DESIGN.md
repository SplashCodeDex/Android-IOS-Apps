# iOS 27 Design Theory System

> **Source of truth:** Apple's official iOS & iPadOS 27 UI Kit ([Figma](https://www.figma.com/community/file/1651309003795292092/ios-and-ipados-27)) and the [WWDC 2026 showcase image](https://devimages-cdn.apple.com/wwdc-services/articles/images/E92B44C5-04EA-42F4-BB2A-AF394EFED6BB/2048.jpeg).
>
> Every component, token, and behavior in this project **must** trace back to this document. If it is not here, it does not ship.

---

## 0. Critical Design Rules

### Anti-Patterns (Strictly Forbidden)

| Forbidden | Why | What To Do Instead |
|:---|:---|:---|
| Decorative gradients | AI slop; iOS uses flat tints and translucent materials, never rainbow/gradient fills | Use **Liquid Glass materials** for functional layers; **solid semantic colors** for content |
| Emoji as UI icons | Unprofessional; renders inconsistently across platforms | Use **SF Symbols** style monochrome glyphs (line-weight ~1.5pt, optically centered) |
| Inter font anywhere | Overused default; misaligns with iOS typographic voice | Use **system default** (`FontFamily.Default` which maps to SF Pro on iOS, Roboto on Android) |
| Uniform component sizing | Every button/card same size looks lifeless | Use **Dynamic Type** scale; size components to their **content role** and **touch target** requirements |
| Drop shadows on content cards | iOS grouped lists are flat; shadow is a Material Design pattern | Use **background color layering** to establish hierarchy. Shadow only on **floating glass** elements (very subtle, diffused) |
| Hardcoded hex in components | Breaks dark mode, breaks accessibility | Always reference **semantic color tokens** |
| Rounded corners via simple radius | iOS uses continuous superellipse ("squircle"), not CSS border-radius | Use **`RoundedCornerShape`** with values from the shape scale (approximation); on iOS native use `continuous` corner style |

---

## 1. Visual Philosophy — Liquid Glass

iOS 27 introduces **Liquid Glass**: a translucent, depth-aware material system where functional UI layers (navigation, toolbars, tab bars, sheets, menus) **float above** the content layer. The screenshot reveals the core principles:

### 1.1 The Two-Layer Architecture

```
┌─────────────────────────────────────────────────┐
│  GLASS LAYER (Functional)                       │
│  ┌──────────────────────────────────────────┐   │
│  │  Nav Bars, Tab Bars, Toolbars, Menus,    │   │
│  │  Alerts, Sheets, Context Menus, Keyboards│   │
│  │  ──────────────────────────────────       │   │
│  │  Semi-transparent + Backdrop blur         │   │
│  │  Thin specular border (1px white @ 8%)    │   │
│  │  Continuous corner shapes                 │   │
│  └──────────────────────────────────────────┘   │
│                                                  │
│  CONTENT LAYER (Informational)                   │
│  ┌──────────────────────────────────────────┐   │
│  │  Lists, Cards, Text, Images, Media       │   │
│  │  ──────────────────────────────────       │   │
│  │  Opaque solid backgrounds                 │   │
│  │  Semantic color fills                     │   │
│  │  No blur, no translucency                 │   │
│  └──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
```

### 1.2 Liquid Glass Material Properties

Observed from the screenshot — every glass element shares these visual traits:

| Property | Value (Dark Mode) | Value (Light Mode) |
|:---|:---|:---|
| **Background fill** | `#1C1C1E` at ~55% opacity over blurred backdrop | `#FFFFFF` at ~55% opacity over blurred backdrop |
| **Backdrop blur radius** | 20–40pt | 20–40pt |
| **Specular border** | `#FFFFFF` at 8–12% opacity, 0.5pt width | `#000000` at 5–8% opacity, 0.5pt width |
| **Inner highlight** | Top edge: `#FFFFFF` at 4% opacity | Top edge: `#FFFFFF` at 6% opacity |
| **Shadow** | `#000000` at 15–20%, offset (0, 2pt), blur 10pt | `#000000` at 8%, offset (0, 1pt), blur 6pt |

### 1.3 Glass Variants

| Variant | Opacity | Use Case |
|:---|:---|:---|
| **Regular** | ~55% fill | Navigation bars, tab bars, toolbars, standard glass surfaces |
| **Clear** | ~25% fill | Small floating controls over rich imagery (e.g., media overlays) |
| **Thick** | ~72% fill | Keyboards, alerts, action sheets — maximum legibility |
| **Identity** | 0% / disabled | When glass effect should be toggled off entirely |

### 1.4 Accessibility: Reduce Transparency

When the user enables **Reduce Transparency**, all glass materials degrade to:
- **Opaque** `secondarySystemBackground` fill (no blur, no transparency)
- Borders become `separator` color at full opacity
- All specular effects removed

---

## 2. Color System — iOS Semantic Tokens

### 2.1 Background Colors

Every background color has a **purpose**. Never pick arbitrarily.

| Token | Light | Dark | When To Use |
|:---|:---|:---|:---|
| `systemBackground` | `#FFFFFF` | `#000000` | Root-level screen background |
| `secondarySystemBackground` | `#F2F2F7` | `#1C1C1E` | Secondary content areas, elevated surfaces |
| `tertiarySystemBackground` | `#FFFFFF` | `#2C2C2E` | Third level of hierarchy (content inside secondary) |
| `systemGroupedBackground` | `#F2F2F7` | `#000000` | Root background for grouped list screens (e.g., Settings) |
| `secondarySystemGroupedBackground` | `#FFFFFF` | `#1C1C1E` | Card/row background inside grouped lists |
| `tertiarySystemGroupedBackground` | `#F2F2F7` | `#2C2C2E` | Third level inside grouped lists |

### 2.2 Label (Text) Colors

| Token | Light | Dark | When To Use |
|:---|:---|:---|:---|
| `label` | `#000000` | `#FFFFFF` | Primary text — titles, body copy |
| `secondaryLabel` | `rgba(60,60,67, 0.60)` | `rgba(235,235,245, 0.60)` | Subtitles, supplementary text |
| `tertiaryLabel` | `rgba(60,60,67, 0.30)` | `rgba(235,235,245, 0.30)` | Placeholder text, disabled labels |
| `quaternaryLabel` | `rgba(60,60,67, 0.18)` | `rgba(235,235,245, 0.18)` | Watermarks, very low-priority text |

### 2.3 Fill Colors (Interactive Surfaces)

Visible on the screenshot's toggles, search bar, slider tracks, and segmented controls:

| Token | Light | Dark | When To Use |
|:---|:---|:---|:---|
| `systemFill` | `rgba(120,120,128, 0.20)` | `rgba(120,120,128, 0.36)` | Toggle OFF track, thin overlays |
| `secondarySystemFill` | `rgba(120,120,128, 0.16)` | `rgba(120,120,128, 0.32)` | Gray button backgrounds, search bar fill |
| `tertiarySystemFill` | `rgba(120,120,128, 0.12)` | `rgba(120,120,128, 0.24)` | Segmented control background, text field fill |
| `quaternarySystemFill` | `rgba(120,120,128, 0.08)` | `rgba(120,120,128, 0.18)` | Lightest fill, used for very subtle backgrounds |

### 2.4 Separator Colors

Visible as the thin lines between context menu items and list rows in the screenshot:

| Token | Light | Dark | When To Use |
|:---|:---|:---|:---|
| `separator` | `rgba(60,60,67, 0.29)` | `rgba(84,84,88, 0.60)` | Separators that **do not** extend to full width (inset) |
| `opaqueSeparator` | `#C6C6C8` | `#38383A` | Separators that extend full width |

### 2.5 Tint / Accent Colors

These are the vibrant colored pills, icons, and interactive elements visible throughout the screenshot:

| Token | Light | Dark | Seen In Screenshot As |
|:---|:---|:---|:---|
| `systemBlue` | `#007AFF` | `#0A84FF` | "Allow" button, "Continue" button, "Today" tab icon, slider fill, checkmark circle |
| `systemRed` | `#FF3B30` | `#FF453A` | "Delete" button, "Flag" icon, destructive actions |
| `systemGreen` | `#34C759` | `#30D158` | Toggle ON track, "Play" button accent |
| `systemOrange` | `#FF9500` | `#FF9F0A` | Airplane Mode icon background |
| `systemYellow` | `#FFCC00` | `#FFD60A` | Highlights, stars |
| `systemTeal` | `#30B0C7` | `#40CBE0` | Information accents |
| `systemCyan` | `#32ADE6` | `#64D2FF` | Links on dark backgrounds |
| `systemIndigo` | `#5856D6` | `#5E5CE6` | Deep accent (Reply icon background in screenshot) |
| `systemPurple` | `#AF52DE` | `#BF5AF2` | Creative/media accent |
| `systemPink` | `#FF2D55` | `#FF375F` | Playful accent |
| `systemBrown` | `#A2845E` | `#AC8E68` | Earthy, natural elements |
| `systemGray` | `#8E8E93` | `#8E8E93` | Neutral — unselected tab icons, inactive controls |
| `systemGray2` | `#AEAEB2` | `#636366` | Secondary neutral |
| `systemGray3` | `#C7C7CC` | `#48484A` | Inactive dot indicators, drag handles |
| `systemGray4` | `#D1D1D6` | `#3A3A3C` | Light borders |
| `systemGray5` | `#E5E5EA` | `#2C2C2E` | Background fills |
| `systemGray6` | `#F2F2F7` | `#1C1C1E` | Lightest gray background |

---

## 3. Typography — Dynamic Type Scale

Font: **System Default** (SF Pro on Apple platforms, Roboto on Android).
Never import Inter, Poppins, or any web font.

| Style | Size | Weight | Line Height | Letter Spacing | Usage (from screenshot) |
|:---|:---|:---|:---|:---|:---|
| **Large Title** | 34sp | Regular | 41sp | 0.37sp | Screen titles in navigation bars |
| **Title 1** | 28sp | Regular | 34sp | 0.36sp | Section headings |
| **Title 2** | 22sp | Regular | 28sp | 0.35sp | Subsection headings |
| **Title 3** | 20sp | Regular | 25sp | 0.38sp | Card titles |
| **Headline** | 17sp | Semi-Bold | 22sp | −0.41sp | "Allow Notifications?", list row primary labels, song title "Everything Is Peaceful" |
| **Body** | 17sp | Regular | 22sp | −0.41sp | Alert description text, list row values, button labels |
| **Callout** | 16sp | Regular | 21sp | −0.32sp | Secondary button text |
| **Subhead** | 15sp | Regular | 20sp | −0.24sp | "Not Connected", detail text in list rows |
| **Footnote** | 13sp | Regular | 18sp | −0.08sp | Alert body text, section headers ("ALLOW NOTIFICATIONS?"), supplementary info |
| **Caption 1** | 12sp | Regular | 16sp | 0sp | Timestamps "0:46", tab bar labels "Today" "Games" |
| **Caption 2** | 11sp | Regular | 13sp | 0.07sp | Smallest text — badges, micro labels |

---

## 4. Shape Scale — Continuous Corners (Squircles)

iOS uses **superellipse** (continuous) corner curves, not standard CSS `border-radius`. In Compose, we approximate with `RoundedCornerShape` but the visual intent is always a squircle.

| Token | Radius | Used For |
|:---|:---|:---|
| `micro` | 5dp | Keyboard individual keys |
| `small` | 8dp | Small tags, compact buttons |
| `standard` | 10dp | List section cards, grouped backgrounds, inset cards |
| `comfortable` | 12dp | Glass surfaces, buttons, glass small elements |
| `medium` | 14dp | Alerts, context menus |
| `large` | 16dp | Glass cards, media cards, tab bar capsule |
| `xLarge` | 22dp | Sheets, large modals |
| `xxLarge` | 28dp | Full-screen sheets |
| `searchBar` | 26dp | Search bar capsule |
| `segmentedControl` | 9dp | Segmented control outer + inner segment |
| `pill` | 980dp | Pill buttons (effectively a capsule via absurd radius) |
| `circle` | 50% | Circular buttons, toggle knobs, avatar clips |

---

## 5. Spacing Scale

Base unit: **8pt grid**. Everything aligns to multiples of 4pt (half-grid for micro adjustments).

| Token | Value | Used For |
|:---|:---|:---|
| `xxs` | 2dp | Hairline gaps, separator insets |
| `xs` | 4dp | Tight inner padding, icon-to-text gap in compact contexts |
| `s` | 8dp | Standard inner padding, gap between related elements |
| `m` | 16dp | Standard outer padding, section spacing |
| `l` | 20dp | List inset leading/trailing padding (iOS standard) |
| `xl` | 24dp | Generous section spacing |
| `xxl` | 32dp | Large section breaks |
| `hero` | 48dp | Hero spacing, screen-level vertical padding |
| | | |
| **Semantic Aliases** | | |
| `listInsetHorizontal` | 20dp | Leading + trailing padding for inset grouped lists |
| `listRowVerticalPadding` | 11dp | Top + bottom padding inside each list row |
| `sectionHeaderBottomPadding` | 6dp | Space below section header text |
| `tabBarHeight` | 49dp | Standard tab bar component height |
| `navBarHeight` | 44dp | Navigation bar height (inline) |
| `searchBarHeight` | 36dp | Search bar component height |
| `toolbarHeight` | 44dp | Bottom toolbar height |
| `minimumTouchTarget` | 44dp | Absolute minimum hit area for any interactive element |
| `glassBarEdgeInset` | 8dp | Inset from screen edges for floating glass bars |

---

## 6. Component Inventory

Every component identified from the iOS 27 screenshot, with exact specifications.

---

### 6.1 Buttons

Observed: "Allow" (blue filled), "Don't Allow" (gray plain), "Delete" (red filled), "Continue" (blue filled capsule), "Cancel" (dark/gray capsule), "Play" (green filled with icon), "Cut"/"Copy"/"Paste" (toolbar text buttons).

#### 6.1.1 Filled Button (Primary CTA)

The **"Continue"** and **"Allow"** buttons in the screenshot.

| Property | Value |
|:---|:---|
| **Background** | `systemBlue` (or any tint color) |
| **Text color** | `#FFFFFF` |
| **Typography** | Body (17sp, Regular) or Headline (17sp, Semi-Bold) |
| **Corner radius** | `pill` (full capsule) for standalone; `comfortable` (12dp) for inline |
| **Height** | 44dp minimum (50dp preferred for prominent CTAs) |
| **Horizontal padding** | 20dp |
| **Press state** | Opacity dims to 70%; subtle inward scale (0.97×) |
| **Disabled state** | `systemFill` background, `tertiaryLabel` text |

#### 6.1.2 Destructive Button

The **"Delete"** button — red filled capsule.

| Property | Value |
|:---|:---|
| **Background** | `systemRed` |
| **Text color** | `#FFFFFF` |
| **All other specs** | Same as Filled Button |

#### 6.1.3 Gray / Secondary Button

The **"Cancel"** and **"Don't Allow"** buttons.

| Property | Value |
|:---|:---|
| **Background** | `secondarySystemFill` |
| **Text color** | `label` (primary text color) |
| **Corner radius** | `pill` (full capsule) |
| **Height** | 44dp minimum |
| **Press state** | Background darkens slightly; scale 0.97× |

#### 6.1.4 Tinted Button

Not prominently shown, but a standard iOS variant.

| Property | Value |
|:---|:---|
| **Background** | `systemBlue` at 15% opacity |
| **Text color** | `systemBlue` |
| **Corner radius** | `comfortable` (12dp) |

#### 6.1.5 Plain Text Button

Menu items in the context menu ("Edit Recording", "View Transcript" etc.).

| Property | Value |
|:---|:---|
| **Background** | Transparent |
| **Text color** | `label` (normal) or `systemBlue` (tinted) or `systemRed` (destructive) |
| **Typography** | Body (17sp) |
| **Press state** | Row highlight with `systemFill` |
| **Height** | 44dp |

#### 6.1.6 Circular Icon Button

The **back chevron**, **checkmark circle**, **toolbar dots** visible in the screenshot.

| Property | Value |
|:---|:---|
| **Size** | 28dp (compact) or 36dp (standard) or 44dp (prominent) |
| **Shape** | `circle` |
| **Background** | Glass (for floating) or `tertiarySystemFill` (for inline) or `systemBlue` (for accent) |
| **Icon** | Monochrome glyph, 1.5pt stroke weight, optically centered |
| **Icon color** | `label` (default), `systemBlue` (accent), `#FFFFFF` (on filled) |

#### 6.1.7 Icon-Label Swipe Action

The **Reply**, **Flag**, **Delete** circles with labels below (top-left of screenshot — mail swipe actions).

| Property | Value |
|:---|:---|
| **Icon container** | 52dp circle with tint background (`systemIndigo` for Reply, `systemOrange` for Flag, `systemRed` for Delete) |
| **Icon** | White monochrome glyph, 22dp |
| **Label** | Caption 1 (12sp), `label` color, centered below icon |
| **Spacing** | 4dp between icon circle and label |

#### 6.1.8 Toolbar Button Row

The **"Cut" / "Copy" / "Paste"** horizontal strip and the bottom toolbar icons.

| Property | Value |
|:---|:---|
| **Container** | Horizontal glass capsule, `thick` glass variant, `pill` shape |
| **Button height** | 36dp |
| **Button width** | Content-fitted + 12dp horizontal padding |
| **Separator** | 0.5pt `separator` color between items |
| **Text** | Callout (16sp), `label` color |
| **End arrows** | Chevron left/right for scrolling, 28dp circle, `tertiaryLabel` |

---

### 6.2 Toggle Switch

Visible: Multiple toggles in ON (green) and OFF (gray) states.

| Property | ON State | OFF State |
|:---|:---|:---|
| **Track size** | 51 × 31dp | 51 × 31dp |
| **Track color** | `systemGreen` | `systemFill` |
| **Track shape** | `pill` (15.5dp radius) | `pill` |
| **Knob size** | 27dp circle | 27dp circle |
| **Knob color** | `#FFFFFF` | `#FFFFFF` |
| **Knob shadow** | `#000000` at 15%, blur 4dp, offset (0, 2dp) | Same |
| **Knob position** | Right-aligned (2dp inset from trailing) | Left-aligned (2dp inset from leading) |
| **Animation** | Spring: damping 0.78, stiffness 350 | Same |
| **Touch target** | Entire 51×31 track + 10dp padding = 71×51dp hit area | Same |

---

### 6.3 Segmented Control

Visible: **"Style" | "Text" | "Arrange"** strip in the screenshot.

| Property | Value |
|:---|:---|
| **Outer container** | Height 32dp, `tertiarySystemFill` background, `segmentedControl` (9dp) corner radius |
| **Selected indicator** | White/light glass background (`secondarySystemGroupedBackground`), 7dp corner radius (slightly less than outer), floats inside with 2dp inset |
| **Indicator animation** | Spring slide: damping 0.70, stiffness 450 |
| **Segment text** | Subhead (15sp, Regular) for unselected; Subhead (15sp, Semi-Bold) for selected |
| **Text color** | `label` for selected, `secondaryLabel` for unselected |
| **Segments** | Equal width, divided evenly across container |
| **Minimum segment width** | 60dp |
| **Touch target** | Each segment is a full-height, full-width hit area |

---

### 6.4 Search Bar

Visible: Magnifying glass icon + "Search" placeholder text in a rounded capsule.

| Property | Value |
|:---|:---|
| **Height** | 36dp |
| **Shape** | `searchBar` (26dp radius — nearly capsule) |
| **Background** | `tertiarySystemFill` |
| **Leading icon** | Magnifying glass, 16dp, `secondaryLabel` color, 8dp leading padding |
| **Placeholder text** | "Search", Body (17sp), `tertiaryLabel` color |
| **Input text** | Body (17sp), `label` color |
| **Cancel button** | Appears on focus — plain text "Cancel" in `systemBlue`, slides in from right with spring animation |
| **Focus state** | Background lightens slightly; cursor appears in `systemBlue` |
| **Clear button** | Circular "×" icon, 18dp, `tertiaryLabel`, appears when text is entered |

---

### 6.5 Context Menu

Visible: The floating menu with "Favorite", "Edit Recording", "View Transcript", "Options", "Share", "Copy", "Copy Transcript", "Duplicate".

| Property | Value |
|:---|:---|
| **Container** | Glass material (`thick` variant), `medium` (14dp) corner radius |
| **Width** | 250dp (standard), content-fitted with min 200dp |
| **Item height** | 44dp |
| **Item padding** | 16dp horizontal |
| **Item text** | Body (17sp), `label` color |
| **Item icon** | Trailing, 20dp monochrome glyph, `secondaryLabel` color |
| **Separator** | 0.33pt `separator` color, inset 16dp from leading edge |
| **Destructive item** | `systemRed` text + icon color |
| **Group separator** | Full-width `opaqueSeparator`, 8dp vertical gap between groups |
| **Appear animation** | Scale from anchor point (0.4× → 1.0×), spring: damping 0.68, stiffness 500, simultaneous fade 0→1 |
| **Backdrop** | Background content dims to 60% and scales to 0.92× |
| **Dismiss** | Tap outside, swipe away, or select an item |

---

### 6.6 Alert Dialog

Visible: "Allow Notifications?" dialog with "Don't Allow" and "Allow" buttons.

| Property | Value |
|:---|:---|
| **Container** | Glass material (`thick`), `medium` (14dp) corner radius, fixed width 270dp |
| **Title** | Headline (17sp, Semi-Bold), `label` color, center-aligned |
| **Message** | Footnote (13sp, Regular), `secondaryLabel` color, center-aligned |
| **Title-to-message gap** | 4dp |
| **Message-to-buttons gap** | 16dp |
| **Button layout** | 2 buttons: side-by-side horizontal. 3+ buttons: vertical stack |
| **Button separator** | 0.33pt `separator` color, full width |
| **Button height** | 44dp |
| **Button text** | Body (17sp); default action is Semi-Bold, cancel is Regular |
| **Button colors** | Default: `systemBlue`. Destructive: `systemRed`. Cancel: `systemBlue` (Regular weight) |
| **Button press** | Background fills with `systemFill` |
| **Scrim** | Full-screen overlay `#000000` at 40% opacity |
| **Appear animation** | Scale 1.1× → 1.0×, fade 0 → 1, spring: damping 0.85, stiffness 300 |
| **Padding** | 16dp top, 16dp sides, 0dp bottom (buttons are full-width) |

---

### 6.7 Confirmation Alert (Destructive Variant)

Visible (partially): "Are you sure you want to delete this event?" in bottom-right corner.

Identical to 6.6 Alert except:
- Title indicates destructive intent
- Primary action button text in `systemRed`
- Includes longer descriptive body text

---

### 6.8 Tab Bar

Visible: The **Today | Games | Apps | Arcade** bar (App Store style).

| Property | Value |
|:---|:---|
| **Container** | Glass material (`regular`), `large` (16dp) corner radius, **floating** with `glassBarEdgeInset` (8dp) from screen edges and bottom safe area |
| **Height** | 49dp |
| **Item layout** | Horizontal, equally spaced |
| **Selected icon** | Tint color (`systemBlue`), filled variant of the SF Symbol |
| **Unselected icon** | `systemGray`, outline variant of the SF Symbol |
| **Icon size** | 24dp |
| **Label** | Caption 2 (11sp), same color as icon |
| **Icon-to-label gap** | 2dp |
| **Selection indicator** | None (color change only — no underline or background) |
| **Selection animation** | Spring scale 0.8× → 1.0× on the icon, tween 200ms color crossfade |

---

### 6.9 Navigation Bar

Not shown as a standalone element, but visible through the back chevron button and large title patterns.

#### 6.9.1 Large Title Navigation Bar

| Property | Value |
|:---|:---|
| **Title text** | Large Title (34sp, Bold), `label` color, left-aligned with 20dp leading padding |
| **Bar background** | Transparent when content is scrolled to top; transitions to glass (`regular`) as content scrolls behind |
| **Bar height** | 96dp (44dp bar + 52dp large title area) |
| **Collapse behavior** | On scroll up, large title smoothly shrinks into inline title (17sp, Semi-Bold, center-aligned) in 44dp bar |
| **Back button** | `systemBlue` chevron "‹" (20dp) + optional previous title text |

#### 6.9.2 Inline Navigation Bar

| Property | Value |
|:---|:---|
| **Title text** | Headline (17sp, Semi-Bold), `label` color, center-aligned |
| **Bar background** | Glass (`regular`) when content is scrolled behind |
| **Height** | 44dp |
| **Leading items** | Back chevron, or custom leading button |
| **Trailing items** | Action buttons (1–2, in `systemBlue` or icon form) |

---

### 6.10 Bottom Toolbar

Visible: The icon bar with compose, share, and action icons in glass capsule.

| Property | Value |
|:---|:---|
| **Container** | Glass material (`regular`), anchored to bottom safe area |
| **Height** | 44dp |
| **Item layout** | Icons spaced evenly or grouped |
| **Icon** | Monochrome glyph, 22dp, `systemBlue` (actionable) or `label` (neutral) |
| **Separator** | None between icons (spacing handles visual separation) |
| **Horizontal padding** | 20dp from edges |

---

### 6.11 Settings List Rows (Inset Grouped)

Visible: "Airplane Mode" (with toggle), "Wi-Fi" (with detail + chevron), "Bluetooth" row.

#### 6.11.1 List Section Container

| Property | Value |
|:---|:---|
| **Background** | `secondarySystemGroupedBackground` |
| **Corner radius** | `standard` (10dp) |
| **Section header** | Footnote (13sp, Regular), `secondaryLabel`, UPPERCASE, 20dp leading padding, 6dp bottom padding |
| **Section footer** | Footnote (13sp, Regular), `secondaryLabel`, sentence case, 20dp leading padding, 8dp top padding |
| **Section-to-section gap** | 35dp (header included) |
| **Outer background** | `systemGroupedBackground` |

#### 6.11.2 List Row

| Property | Value |
|:---|:---|
| **Height** | 44dp minimum (content-fitted, grows with multi-line) |
| **Vertical padding** | 11dp top + bottom |
| **Horizontal padding** | 20dp leading + trailing |
| **Leading icon** | 29dp rounded square (6dp corner radius), colored background, white icon inside (17dp) |
| **Icon-to-title gap** | 12dp |
| **Title** | Body (17sp, Regular), `label` color |
| **Detail text** | Subhead (15sp, Regular), `secondaryLabel` color, trailing |
| **Trailing accessory** | Disclosure chevron "›" (14dp, `tertiaryLabel`), or Toggle, or detail text |
| **Separator** | 0.33pt `separator` color, inset from the leading edge of the title text (not from screen edge) |
| **Press state** | Background fills with `systemFill`, 200ms fade |

**Leading Icon Color Mapping** (from screenshot):
| Setting | Icon BG Color | Icon |
|:---|:---|:---|
| Airplane Mode | `systemOrange` | Airplane glyph |
| Wi-Fi | `systemBlue` | Wi-Fi glyph |
| Bluetooth | `systemBlue` | Bluetooth glyph |

---

### 6.12 Slider

Visible: Horizontal blue slider (media player progress and standalone).

| Property | Value |
|:---|:---|
| **Track height** | 4dp |
| **Track filled** | `systemBlue` |
| **Track unfilled** | `systemFill` |
| **Track shape** | `pill` (2dp radius) |
| **Knob size** | 28dp circle |
| **Knob color** | `#FFFFFF` |
| **Knob shadow** | `#000000` at 20%, blur 4dp, offset (0, 1dp) |
| **Touch target** | 44dp × 44dp (centered on knob) |
| **Drag behavior** | Continuous value update; knob scales to 1.1× while dragging |

---

### 6.13 Stepper

Visible: The "−" / "+" horizontal control in the screenshot.

| Property | Value |
|:---|:---|
| **Container** | `secondarySystemFill` background, `comfortable` (12dp) corner radius |
| **Height** | 36dp |
| **Width** | 94dp (47dp per side) |
| **Separator** | 0.5pt `separator` color, vertical, centered |
| **Button icons** | "−" and "+", 20dp, `systemBlue` |
| **Press state** | Background of pressed side fills with `tertiarySystemFill` |
| **Disabled state** | Icon becomes `tertiaryLabel`, no press response |

---

### 6.14 Keyboard

Visible: Full dark-themed QWERTY keyboard.

| Property | Value |
|:---|:---|
| **Container** | `thick` glass material, anchored to bottom |
| **Key background** | `secondarySystemBackground` (letter keys), `tertiarySystemFill` (modifier keys like "123", "return") |
| **Key shape** | `micro` (5dp) corner radius |
| **Key height** | 42dp |
| **Key gap** | 6dp horizontal, 12dp vertical |
| **Key text** | Title 2 (22sp, Regular for letters), Subhead (15sp) for modifiers |
| **Key press** | Pop-up magnified preview above key, 1.1× scale, spring animation |
| **Special keys** | Microphone icon (dictation), globe icon (language), "return" label |

---

### 6.15 Media / Now Playing Card

Visible: Album art + "Everything Is Peaceful" by "Bon Iver" with transport controls.

| Property | Value |
|:---|:---|
| **Card container** | Glass material (`regular`), `large` (16dp) corner radius |
| **Album art** | Full-bleed or inset, `comfortable` (12dp) corner radius, aspect ratio 1:1 |
| **Song title** | Headline (17sp, Semi-Bold), `label` |
| **Artist** | Subhead (15sp, Regular), `secondaryLabel` |
| **Progress bar** | 2dp height, `systemBlue` fill, `systemFill` track, `pill` shape |
| **Time labels** | Caption 1 (12sp), `secondaryLabel`, flanking progress bar |
| **Transport icons** | Rewind (22dp), Play/Pause (32dp), Forward (22dp), `label` color |
| **Transport spacing** | 32dp between controls |

---

### 6.16 Notification Banner

Visible: "Shira Salehi — Leaving now" with avatar.

| Property | Value |
|:---|:---|
| **Container** | Glass material (`regular`), `large` (16dp) corner radius, drops from top with spring |
| **Avatar** | 36dp circle, clipped |
| **Title** | Headline (17sp, Semi-Bold), `label` |
| **Subtitle** | Subhead (15sp, Regular), `secondaryLabel` |
| **Timestamp** | Caption 1 (12sp), `tertiaryLabel`, trailing |
| **Padding** | 12dp all sides |
| **Appear** | Slide from top, spring: damping 0.80, stiffness 350 |
| **Dismiss** | Swipe up, or auto-dismiss after 5 seconds |

---

### 6.17 Date/Time Picker

Visible: "3:00" with "AM"/"PM" segmented selector.

| Property | Value |
|:---|:---|
| **Layout** | Horizontal: time display + AM/PM segment |
| **Time display** | Title 1 (28sp, Regular), `label`, inside `tertiarySystemFill` rounded rect |
| **AM/PM** | Vertical segmented control, `tertiarySystemFill` bg, `segmentedControl` corners, selected segment uses `secondarySystemGroupedBackground` |
| **Selection** | Sliding glass indicator, same as segmented control behavior |

---

### 6.18 Edit Menu (Text Selection)

Visible: Horizontal **"Cut" | "Copy" | "Paste"** bar with scroll arrows.

| Property | Value |
|:---|:---|
| **Container** | `thick` glass material, `pill` shape, height 36dp |
| **Items** | Horizontal, separated by 0.5pt `separator` |
| **Item text** | Callout (16sp, Regular), `label` |
| **Item padding** | 12dp horizontal |
| **Scroll arrows** | "‹" and "›" at ends, 28dp, `tertiaryLabel`, appear when more items overflow |
| **Appear** | Fade + scale from selection point, spring: damping 0.72, stiffness 400 |

---

### 6.19 Activity Indicator (Spinner)

Visible: "Syncing..." label with implied spinner.

| Property | Value |
|:---|:---|
| **Size** | 20dp (standard), 36dp (large) |
| **Design** | 12-spoke wheel/arc, strokes with opacity gradient from 100% to 10% |
| **Color** | `secondaryLabel` (default), or `#FFFFFF` on dark/colored backgrounds |
| **Rotation** | Continuous, 1 revolution per second, stepped (12 discrete positions) |

---

### 6.20 Progress Bar (Linear)

| Property | Value |
|:---|:---|
| **Track height** | 4dp |
| **Track background** | `systemFill` |
| **Fill** | `systemBlue` (or tint color) |
| **Shape** | `pill` (2dp radius, rounded caps) |
| **Animation** | Fill width animates with tween 300ms, EaseInOut |
| **Indeterminate** | Pulsing shimmer bar that slides left → right, 2 seconds per cycle |

---

### 6.21 Page Control (Dots)

Not directly visible but part of the standard iOS kit.

| Property | Value |
|:---|:---|
| **Dot diameter** | 7dp |
| **Dot gap** | 8dp center-to-center = 1dp visible gap |
| **Active dot** | `label` color (full opacity) |
| **Inactive dot** | `systemGray3` (or `label` at 30% opacity) |
| **Current page indicator** | Scales to 1.3× + color change, spring animation |

---

### 6.22 Action Sheet

Not directly visible but a standard companion to the context menu.

| Property | Value |
|:---|:---|
| **Container** | Glass material (`thick`), `medium` (14dp) corner radius, anchored to bottom |
| **Title** | Footnote (13sp), `secondaryLabel`, center-aligned |
| **Message** | Footnote (13sp), `secondaryLabel`, center-aligned |
| **Action buttons** | Full-width, 57dp height, Body (17sp), `systemBlue` (or `systemRed` for destructive) |
| **Button separators** | 0.33pt `separator` color |
| **Cancel button** | Separate group below with 8dp gap, Semi-Bold text, own glass container |
| **Appear** | Slide up from bottom, spring: damping 0.85, stiffness 300 |
| **Scrim** | `#000000` at 40% |

---

### 6.23 Sheet (Modal Presentation)

| Property | Value |
|:---|:---|
| **Container** | `xLarge` (22dp) corner radius, glass or opaque `systemBackground` |
| **Drag handle** | Centered pill, 36 × 5dp, `systemGray3`, 6dp from top edge |
| **Detents** | Medium (~50% screen), Large (~93% screen), custom heights |
| **Background dimming** | Behind sheet: content scales to 0.92× and corner-rounds to 10dp |
| **Slide animation** | Spring: damping 0.85, stiffness 300 |
| **Dismiss** | Swipe down past threshold, tap scrim |

---

### 6.24 Text Field

| Property | Value |
|:---|:---|
| **Variant A — Plain** | No visible border; only a bottom separator 0.33pt `separator` |
| **Variant B — Rounded** | `tertiarySystemFill` background, `standard` (10dp) corners, 8dp horizontal + 7dp vertical padding |
| **Placeholder** | `tertiaryLabel` color |
| **Input text** | Body (17sp), `label` color |
| **Cursor** | 2dp wide, `systemBlue`, blinking |
| **Clear button** | Circular "×", 18dp, `tertiaryLabel`, appears when text is present |
| **Focus state** | Rounded variant: subtle `systemBlue` border at 30% opacity |

---

## 7. Animation Specifications

All animations use **spring physics** (not linear/ease curves) unless noted.

| Animation | Damping Ratio | Stiffness | Use Case |
|:---|:---|:---|:---|
| `liquidSpring` | 0.78 | 350 | General glass morphing, element transitions |
| `pressScale` | Medium Bouncy | Medium-Low | Button press/release (scale 1.0 → 0.95 → 1.0) |
| `tabBarTransition` | 0.80 | 400 | Tab bar item switching |
| `sheetSlide` | 0.85 | 300 | Sheet presentation and dismissal |
| `glassGlow` | — | — | `tween(200ms, EaseInOut)` — Button press glow effect |
| `segmentSlide` | 0.70 | 450 | Segmented control indicator sliding |
| `contextMenuScale` | 0.68 | 500 | Context menu pop-in from anchor |
| `toggleKnob` | 0.78 | 350 | Toggle knob slide left ↔ right |
| `searchCancel` | 0.80 | 400 | Cancel button slide-in on search focus |
| `notificationDrop` | 0.80 | 350 | Notification banner drop from top |
| `fadeIn` | — | — | `tween(300ms)` — Generic fade entrance |
| `slideUp` | Low Bouncy | Low | Content slide-up entrance |

---

## 8. Iconography

### 8.1 Icon Style Rules

- **Weight:** 1.5pt stroke, consistent across all icons
- **Style:** Monochrome, single-color, line-art (not filled by default; filled variant used for selected states)
- **Grid:** 24 × 24dp bounding box; 20dp optical area, 2dp optical padding
- **Color:** Inherits from parent — `label`, `secondaryLabel`, `systemBlue`, or `#FFFFFF` depending on context
- **Source:** SF Symbols style equivalents — never emoji, never multicolor, never illustrative

### 8.2 Icon Sizing by Context

| Context | Bounding Box | Optical Size |
|:---|:---|:---|
| Tab bar | 24dp | 22dp |
| Navigation bar button | 22dp | 20dp |
| List row leading icon | 17dp (inside 29dp colored square) | 15dp |
| Toolbar | 22dp | 20dp |
| Inline text | 16dp | 14dp |
| Button leading icon | 16dp | 14dp |

---

## 9. Implementation Notes for Compose Multiplatform

### 9.1 Liquid Glass in KMP

Since Compose Multiplatform renders via Skia (not native UIKit), true system-level blur is not available cross-platform. The strategy:

| Platform | Approach |
|:---|:---|
| **iOS** | Use native SwiftUI shell for system bars where possible; for custom glass, use **Haze** library |
| **Android** | Use **Haze** library (`dev.chrisbanes.haze`) for performant backdrop blur |
| **Desktop** | Use **Haze** library; falls back to semi-transparent overlay on low-end hardware |
| **Reduced Transparency** | Detect accessibility setting → replace all glass with opaque `secondarySystemBackground` |

### 9.2 Squircle Corners

Compose's `RoundedCornerShape` uses standard circular arcs. iOS uses superellipse (continuous) curves. For v1, we approximate with `RoundedCornerShape`. For v2, we can implement a custom `GenericShape` that draws a superellipse path.

### 9.3 Dark Mode Is Primary

The screenshot is entirely in **dark mode**. iOS 27's Liquid Glass looks most striking in dark mode. All development should be tested dark-mode-first, then verified in light mode.

---

## 10. Component Implementation Checklist

| # | Component | Tokens Needed | Priority |
|:---|:---|:---|:---|
| 1 | Liquid Glass Material | glass colors, blur, border | P0 — Foundation |
| 2 | Filled Button | tint colors, pill shape, press animation | P0 |
| 3 | Gray Button | fill colors, pill shape | P0 |
| 4 | Destructive Button | systemRed, pill shape | P0 |
| 5 | Toggle Switch | systemGreen, systemFill, spring animation | P0 |
| 6 | List Row (Inset Grouped) | semantic backgrounds, separator, leading icon | P0 |
| 7 | List Section | grouped backgrounds, section header/footer | P0 |
| 8 | Search Bar | fill colors, searchBar shape | P0 |
| 9 | Segmented Control | fill colors, sliding indicator | P0 |
| 10 | Navigation Bar (Large + Inline) | glass material, large title typography | P0 |
| 11 | Tab Bar (Floating Glass) | glass material, tint colors | P0 |
| 12 | Alert Dialog | thick glass, button layout | P1 |
| 13 | Context Menu | thick glass, menu items, separator | P1 |
| 14 | Action Sheet | thick glass, bottom anchor, cancel group | P1 |
| 15 | Sheet (Modal) | drag handle, detents, scrim | P1 |
| 16 | Slider | systemBlue track, white knob, shadow | P1 |
| 17 | Stepper | fill bg, ± buttons, separator | P1 |
| 18 | Toolbar | glass material, action icons | P1 |
| 19 | Text Field (Plain + Rounded) | separator, placeholder, cursor | P2 |
| 20 | Date/Time Picker | segmented AM/PM, wheel | P2 |
| 21 | Progress Bar (Linear) | systemBlue fill | P2 |
| 22 | Activity Indicator (Spinner) | 12-spoke, rotation | P2 |
| 23 | Page Control (Dots) | dot sizing, active/inactive | P2 |
| 24 | Notification Banner | glass card, avatar, slide-in | P2 |
| 25 | Media/Now Playing Card | album art, transport controls | P2 |
| 26 | Keyboard (Reference Only) | — | P3 (system-provided) |
| 27 | Edit Menu | glass capsule, text items | P3 |
| 28 | Swipe Actions | colored circles + labels | P3 |
