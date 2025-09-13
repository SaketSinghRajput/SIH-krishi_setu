package com.example.krishisetu.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import com.example.krishisetu.ui.theme.GreenPrimary
import com.example.krishisetu.ui.theme.GreenDark
import com.example.krishisetu.ui.theme.BrownEarth
import com.example.krishisetu.ui.theme.YellowHarvest
import com.example.krishisetu.ui.theme.BlueSecondary
import com.example.krishisetu.ui.theme.WhiteBackground

private val DarkColorScheme = darkColorScheme(
    primary = GreenDark,
    secondary = BrownEarth,
    tertiary = YellowHarvest,
    background = WhiteBackground,
    surface = BrownEarth,
    onPrimary = WhiteBackground,
    onSecondary = WhiteBackground,
    onTertiary = WhiteBackground,
    onBackground = GreenDark,
    onSurface = GreenDark
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = BrownEarth,
    tertiary = YellowHarvest,
    background = WhiteBackground,
    surface = WhiteBackground,
    onPrimary = WhiteBackground,
    onSecondary = WhiteBackground,
    onTertiary = WhiteBackground,
    onBackground = GreenDark,
    onSurface = GreenDark
)

@Composable
fun KrishisetuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
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