package com.forkly.app.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.forkly.app.ui.theme.*

/**
 * Reusable Forkly design-system primitives. These establish consistent styling for the UI
 * redesign phases without changing individual screens. Existing feature components may adopt
 * them incrementally; behavior and app logic remain unchanged.
 */

@Composable
fun ForklyCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = ForklyRadii.card,
    color: Color = MaterialTheme.colorScheme.forklySurfacePanel,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    border: BorderStroke? = BorderStroke(ForklyDimens.borderWidth, MaterialTheme.colorScheme.forklyBorder),
    tonalElevation: Dp = ForklyDimens.elevationNone,
    shadowElevation: Dp = ForklyDimens.elevationNone,
    contentPadding: PaddingValues = PaddingValues(ForklySpacing.md),
    content: @Composable ColumnScope.() -> Unit,
) {
    val glass = LocalHarnessGlass.current.mode != HarnessGlassMode.OFF
    val body: @Composable () -> Unit = { Column(modifier = Modifier.padding(contentPadding), content = content) }
    if (onClick != null) {
        HarnessGlassSurface(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            color = color,
            contentColor = contentColor,
            tonalElevation = if (glass) 0.dp else tonalElevation,
            shadowElevation = shadowElevation,
            border = if (glass) null else border,
            content = body,
        )
    } else {
        HarnessGlassSurface(
            modifier = modifier,
            shape = shape,
            color = color,
            contentColor = contentColor,
            tonalElevation = if (glass) 0.dp else tonalElevation,
            shadowElevation = shadowElevation,
            border = if (glass) null else border,
            content = body,
        )
    }
}

@Composable
fun ForklyOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    shape: Shape = ForklyRadii.input,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.heightIn(min = ForklyDimens.inputMinHeight),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        isError = isError,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = shape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.forklyBorder,
            errorBorderColor = MaterialTheme.colorScheme.error,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
    )
}

enum class ForklyStatusTone { NEUTRAL, PRIMARY, SUCCESS, WARNING, ERROR }

@Composable
fun ForklyStatusChip(
    label: String,
    modifier: Modifier = Modifier,
    tone: ForklyStatusTone = ForklyStatusTone.NEUTRAL,
    icon: ImageVector? = null,
) {
    val status = LocalForklyStatusColors.current
    val (container, content) = when (tone) {
        ForklyStatusTone.NEUTRAL -> MaterialTheme.colorScheme.surfaceContainerHigh to MaterialTheme.colorScheme.onSurfaceVariant
        ForklyStatusTone.PRIMARY -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
        ForklyStatusTone.SUCCESS -> status.successContainer to status.onSuccessContainer
        ForklyStatusTone.WARNING -> status.warningContainer to status.onWarningContainer
        ForklyStatusTone.ERROR -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
    }
    Surface(
        modifier = modifier.semantics { contentDescription = label },
        shape = ForklyRadii.pill,
        color = container,
        contentColor = content,
    ) {
        Row(
            Modifier.padding(horizontal = ForklySpacing.xs, vertical = ForklySpacing.xxs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(ForklySpacing.xxs),
        ) {
            if (icon != null) Icon(icon, null, Modifier.size(ForklyDimens.iconSm))
            Text(label, style = MaterialTheme.typography.labelMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun ForklyStatusDot(
    modifier: Modifier = Modifier,
    tone: ForklyStatusTone = ForklyStatusTone.NEUTRAL,
    label: String? = null,
) {
    val status = LocalForklyStatusColors.current
    val color = when (tone) {
        ForklyStatusTone.NEUTRAL -> MaterialTheme.colorScheme.outline
        ForklyStatusTone.PRIMARY -> MaterialTheme.colorScheme.primary
        ForklyStatusTone.SUCCESS -> status.success
        ForklyStatusTone.WARNING -> status.warning
        ForklyStatusTone.ERROR -> MaterialTheme.colorScheme.error
    }
    Surface(
        modifier = modifier
            .size(ForklyDimens.statusDot)
            .then(if (label != null) Modifier.semantics { contentDescription = label } else Modifier),
        shape = CircleShape,
        color = color,
    ) {}
}

@Composable
fun ForklySectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
