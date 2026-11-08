package com.forkly.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Forkly design tokens: consistent spacing, dimensions, elevations and component metrics.
 *
 * Use these instead of ad-hoc dp literals in shared components and new UI. Existing screens
 * may still use literals; this establishes the design system for the Forkly UI redesign.
 * Tokens are chosen for a premium developer-workstation feel: calm density, clear hierarchy,
 * and comfortable touch targets across screen sizes and font scaling.
 */
@Immutable
object ForklySpacing {
    val xxxs: Dp = 2.dp
    val xxs: Dp = 4.dp
    val xs: Dp = 8.dp
    val sm: Dp = 12.dp
    val md: Dp = 16.dp
    val lg: Dp = 20.dp
    val xl: Dp = 24.dp
    val xxl: Dp = 32.dp
    val xxxl: Dp = 40.dp

    /** Horizontal screen margin for primary content. */
    val screenHorizontal: Dp = 16.dp
    /** Vertical rhythm between major sections. */
    val sectionVertical: Dp = 20.dp
    /** Gap inside cards and grouped surfaces. */
    val contentGap: Dp = 12.dp
}

@Immutable
object ForklyDimens {
    /** Minimum touch target for interactive controls (accessibility). */
    val minTouchTarget: Dp = 48.dp
    /** Compact touch target for dense toolbars/chips where layout is constrained. */
    val compactTouchTarget: Dp = 40.dp

    val iconXs: Dp = 14.dp
    val iconSm: Dp = 16.dp
    val iconMd: Dp = 20.dp
    val iconLg: Dp = 24.dp
    val iconXl: Dp = 32.dp

    val buttonHeight: Dp = 40.dp
    val buttonHeightCompact: Dp = 36.dp
    val inputMinHeight: Dp = 48.dp
    val chipHeight: Dp = 32.dp
    val statusDot: Dp = 8.dp

    val borderWidth: Dp = 1.dp
    val focusBorderWidth: Dp = 2.dp
    val dividerThickness: Dp = 1.dp

    val elevationNone: Dp = 0.dp
    val elevationSubtle: Dp = 1.dp
    val elevationRaised: Dp = 2.dp
    val elevationOverlay: Dp = 6.dp
}

@Immutable
object ForklyRadii {
    val control = RoundedCornerShape(8.dp)
    val input = RoundedCornerShape(12.dp)
    val card = RoundedCornerShape(16.dp)
    val dialog = RoundedCornerShape(24.dp)
    val sheet = RoundedCornerShape(28.dp)
    val pill = RoundedCornerShape(999.dp)
}

/**
 * Semantic status roles that complement Material 3's [ColorScheme]. M3 has no success/warning;
 * error lives in the scheme. [LocalStatusColors] provides these with light/dark-aware values.
 */
@Immutable
class ForklyStatusColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
)

internal val ForklyStatusLight = ForklyStatusColors(
    success = Color(0xFF1B7A43),
    onSuccess = Color(0xFFFFFFFF),
    successContainer = Color(0xFFD2F3DE),
    onSuccessContainer = Color(0xFF0A2A16),
    warning = Color(0xFF8A5A00),
    onWarning = Color(0xFFFFFFFF),
    warningContainer = Color(0xFFFFE4B8),
    onWarningContainer = Color(0xFF2D1C00),
)

internal val ForklyStatusDark = ForklyStatusColors(
    success = Color(0xFF7ED9A3),
    onSuccess = Color(0xFF06391C),
    successContainer = Color(0xFF1E4D33),
    onSuccessContainer = Color(0xFFD2F3DE),
    warning = Color(0xFFF2C96D),
    onWarning = Color(0xFF452C00),
    warningContainer = Color(0xFF5C3F00),
    onWarningContainer = Color(0xFFFFE4B8),
)

val LocalForklyStatusColors = staticCompositionLocalOf { ForklyStatusLight }

/** Border color for restrained developer-workstation surfaces. */
val ColorScheme.forklyBorder: Color
    @Composable @ReadOnlyComposable get() = outlineVariant.copy(alpha = 0.72f)

/** Stronger border for focused/selected controls. */
val ColorScheme.forklyBorderStrong: Color
    @Composable @ReadOnlyComposable get() = outline.copy(alpha = 0.85f)

/** Subtle elevated surface layered above [surface]. */
val ColorScheme.forklySurfaceRaised: Color
    @Composable @ReadOnlyComposable get() = surfaceContainerLow

/** Grouping surface for cards/panels. */
val ColorScheme.forklySurfacePanel: Color
    @Composable @ReadOnlyComposable get() = surfaceContainer

object ForklyThemeTokens {
    val spacing: ForklySpacing = ForklySpacing
    val dimens: ForklyDimens = ForklyDimens
    val radii: ForklyRadii = ForklyRadii
    val statusColors: ForklyStatusColors
        @Composable @ReadOnlyComposable get() = LocalForklyStatusColors.current
}
