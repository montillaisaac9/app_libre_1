package com.example.electiva_libre.presentacion.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = primaryColorDark,
    background = bgDark,
    onPrimary= Black,
    secondary = secundaryDark,
    tertiary = Blue,
    onBackground = White,
    onSecondaryContainer= BlackCard,
    secondaryContainer = secundaryDark,
    surfaceContainerHigh = BackgroundSearchDark,
    onTertiary = White,
    onTertiaryContainer = White,
    tertiaryContainer = GrayLight
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    background = bgLight,
    onPrimary= White,
    secondary = secundary,
    onSecondaryContainer= White,
    secondaryContainer = Gray,
    tertiary = secundary,
    onBackground= Black,
    surfaceContainerHigh = BackgroundSearch,
    onTertiary = Black,
    onTertiaryContainer = BlueDark




    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Electiva_LibreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}