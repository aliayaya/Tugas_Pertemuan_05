package com.example.katalogmatakuliah.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkSlateColorScheme = darkColorScheme(
    primary = SlateAccentPrimary,
    onPrimary = SlateDarkBackground,
    primaryContainer = SlateAccentPrimaryContainer,
    onPrimaryContainer = SlateAccentOnPrimaryContainer,
    secondary = SlateAccentSecondary,
    onSecondary = SlateDarkBackground,
    secondaryContainer = SlateAccentSecondaryContainer,
    background = SlateDarkBackground,
    onBackground = SlateTextPrimary,
    surface = SlateDarkSurface,
    onSurface = SlateTextPrimary,
    surfaceVariant = SlateDarkSurfaceVariant,
    onSurfaceVariant = SlateTextSecondary,
    outline = SlateDarkBorder,
    error = SlateError,
    errorContainer = SlateErrorContainer
)

@Composable
fun KatalogMataKuliahTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkSlateColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
