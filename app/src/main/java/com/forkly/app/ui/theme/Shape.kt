package com.forkly.app.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Corner radii for the flat, professional look.
 *
 * Tighter than the M3 Expressive defaults: cards and sheets sit at 12–16dp
 * (calmer than the old 22–28dp squircles), small controls at 6–8dp.
 * True pills (composer, chips) are still created inline with CircleShape.
 * For component-specific tokens use [ForklyRadii] in [DesignTokens.kt].
 */
val HarnessShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

/** Stable shape references for the Forkly design system; [HarnessShapes] remains the M3 mapping. */
object ForklyComponentShapes {
    val button = RoundedCornerShape(10.dp)
    val card = RoundedCornerShape(16.dp)
    val input = RoundedCornerShape(12.dp)
    val dialog = RoundedCornerShape(24.dp)
    val bottomSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    val chip = CircleShape
}

